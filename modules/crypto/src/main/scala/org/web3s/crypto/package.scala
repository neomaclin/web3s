package org.web3s

import org.bouncycastle.crypto.digests.SHA256Digest
import org.bouncycastle.crypto.params.ECPrivateKeyParameters
import org.bouncycastle.crypto.signers.{ECDSASigner, HMacDSAKCalculator}
import org.bouncycastle.jcajce.provider.asymmetric.ec.{BCECPrivateKey, BCECPublicKey}

import org.web3s.crypto.{ECDSASignature, ECKeyPair, Keys, Sign}
import org.web3s.utils.Numeric

import java.security.KeyPair
import java.util
import java.security.SecureRandom


package crypto:

  object SecureRandomUtils:
    def secureRandom = new SecureRandom
  end SecureRandomUtils


  object Credentials:

    def create(ecKeyPair: ECKeyPair): Credentials =
      Credentials(ecKeyPair, Numeric.prependHexPrefix(Keys.getAddress(ecKeyPair)))

    def create(privateKey: String, publicKey: String): Credentials =
      create(ECKeyPair(Numeric.toBigInt(privateKey), Numeric.toBigInt(publicKey)))

    def create(privateKey: String): Credentials =
      create(ECKeyPair.create(Numeric.toBigInt(privateKey)))

  end Credentials

  final case class Credentials(ecKeyPair: ECKeyPair, address: String)

  final case class Bip39Wallet(filename: String, mnemonic: String)

  final case class HSMHTTPPass(address: String, publicKey: BigInt, url: String)

  final case class HSMPass(address: String, publicKey: BigInt)

  final case class ECDSASignature(r: BigInt, s: BigInt):

    def isCanonical: Boolean = s.compareTo(Sign.HALF_CURVE_ORDER) <= 0

    def toCanonicalised: ECDSASignature = 
      if (isCanonical) this else ECDSASignature(r, Sign.CURVE.getN.subtract(s.bigInteger))
    // If S is in the upper half of the number of valid points, then bring it back to
    // the lower half. Otherwise, imagine that
    //    N = 10
    //    s = 8, so (-8 % 10 == 2) thus both (r, 8) and (r, 2) are valid solutions.
    //    10 - 8 == 2, giving us always the latter solution, which is canonical.
    // The order of the curve is the number of valid points that exist on that curve.
  end ECDSASignature

  object ECKeyPair:

    def create(keyPair: KeyPair): ECKeyPair =
      val privateKey = keyPair.getPrivate.asInstanceOf[BCECPrivateKey]
      val publicKey = keyPair.getPublic.asInstanceOf[BCECPublicKey]
      val privateKeyValue = privateKey.getD
      // Ethereum does not use encoded public keys like bitcoin - see
      // https://en.bitcoin.it/wiki/Elliptic_Curve_Digital_Signature_Algorithm for details
      // Additionally, as the first bit is a constant prefix (0x04) we ignore this value
      val publicKeyBytes = publicKey.getQ.getEncoded(false)
      val publicKeyValue = BigInt(1, publicKeyBytes.drop(1))
      ECKeyPair(privateKeyValue, publicKeyValue)
    end create

    def create(privateKey: BigInt): ECKeyPair = ECKeyPair(privateKey, Sign.publicKeyFromPrivate(privateKey))

    def create(privateKey: Array[Byte]): ECKeyPair = create(Numeric.toBigInt(privateKey))

  end ECKeyPair


  final case class ECKeyPair(privateKey: BigInt, publicKey: BigInt):

    def sign(transactionHash: Array[Byte]): ECDSASignature =
      val signer = new ECDSASigner(new HMacDSAKCalculator(new SHA256Digest))
      val privKey = new ECPrivateKeyParameters(privateKey.bigInteger, Sign.CURVE)
      signer.init(true, privKey)
      val components = signer.generateSignature(transactionHash)
      ECDSASignature(components(0), components(1)).toCanonicalised
    end sign

  end ECKeyPair

end crypto
