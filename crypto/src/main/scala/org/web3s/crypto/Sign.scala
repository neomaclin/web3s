
package org.web3s.crypto

import org.bouncycastle.asn1.x9.{X9ECParameters, X9IntegerConverter}
import org.bouncycastle.crypto.ec.CustomNamedCurves
import org.bouncycastle.crypto.params.ECDomainParameters
import org.bouncycastle.math.ec.custom.sec.SecP256K1Curve
import org.bouncycastle.math.ec.{ECAlgorithms, ECPoint, FixedPointCombMultiplier}
import org.web3s.crypto.transaction.Transaction
import org.web3s.utils.Assertions.verifyPrecondition
import org.web3s.utils.Numeric

import java.security.SignatureException

object Sign:

  val CURVE_PARAMS: X9ECParameters = CustomNamedCurves.getByName("secp256k1")
  val CURVE = new ECDomainParameters(CURVE_PARAMS.getCurve, CURVE_PARAMS.getG, CURVE_PARAMS.getN, CURVE_PARAMS.getH)
  val HALF_CURVE_ORDER: BigInt = CURVE_PARAMS.getN.shiftRight(1)
  val MESSAGE_PREFIX: String = "\u0019Ethereum Signed Message:\n"

  final case class SignatureData(v: Array[Byte], r: Array[Byte], s: Array[Byte]):
    def isEmpty: Boolean = v.isEmpty && r.isEmpty && s.isEmpty
    def nonEmpty: Boolean = !isEmpty
    override def equals(o: Any): Boolean = o match
      case SignatureData(ov,or,os) => (v sameElements ov) && (r sameElements or) && (s sameElements os)
      case _ => false

  end SignatureData

  def ethereumMessagePrefix(messageLength: Int): Array[Byte] =
    MESSAGE_PREFIX.concat(String.valueOf(messageLength)).getBytes

  def ethereumMessageHash(message: Array[Byte]): Array[Byte] =
    val prefix = ethereumMessagePrefix(message.length)
    val result = new Array[Byte](prefix.length + message.length)
    System.arraycopy(prefix, 0, result, 0, prefix.length)
    System.arraycopy(message, 0, result, prefix.length, message.length)
    Hash.sha3(result)
  end ethereumMessageHash

  def signPrefixedMessage(message: Array[Byte], keyPair: ECKeyPair): Sign.SignatureData =
    signMessage(ethereumMessageHash(message), keyPair, false)

  def signMessage(message: Array[Byte], keyPair: ECKeyPair): Sign.SignatureData =
    signMessage(message, keyPair, true)

  def signMessage(message: Array[Byte], keyPair: ECKeyPair, needToHash: Boolean): Sign.SignatureData =
    val publicKey = keyPair.publicKey
    val messageHash = if (needToHash) Hash.sha3(message) else message
    val sig = keyPair.sign(messageHash)
    createSignatureData(sig, publicKey, messageHash)
  end signMessage

  def createSignatureData(sig: ECDSASignature, publicKey: BigInt, messageHash: Array[Byte]): Sign.SignatureData =
    val recId = (0 until 4).find(recoverFromSignature(_, sig, messageHash) == publicKey).getOrElse(-1)
    if (recId == -1) throw new RuntimeException("Could not construct a recoverable key. Are your credentials valid?")
    val headerByte = recId + 27
     // 1 header + 32 bytes for R + 32 bytes for S
    val v = Array[Byte](headerByte.toByte)
    val r = Numeric.toBytesPadded(sig.r, 32)
    val s = Numeric.toBytesPadded(sig.s, 32)
    Sign.SignatureData(v, r, s)
  end createSignatureData

  def recoverFromSignature(recId: Int, sig: ECDSASignature, message: Array[Byte]): BigInt =
     verifyPrecondition(recId >= 0, "recId must be positive")
     verifyPrecondition(sig.r.signum >= 0, "r must be positive")
     verifyPrecondition(sig.s.signum >= 0, "s must be positive")
     verifyPrecondition(message.nonEmpty, "message cannot be null")
  //   // 1.0 For j from 0 to h   (h == recId here and the loop is outside this function)
  //   //   1.1 Let x = r + jn
     val n = CURVE.getN // Curve order.
     val i = BigInt(recId.toLong / 2)
     val x = sig.r + i * n
  //   //   1.2. Convert the integer x to an octet string X of length mlen using the conversion
  //   //        routine specified in Section 2.3.7, where mlen = ⌈(log2 p)/8⌉ or mlen = ⌈m/8⌉.
  //   //   1.3. Convert the octet string (16 set binary digits)||X to an elliptic curve point R
  //   //        using the conversion routine specified in Section 2.3.4. If this conversion
  //   //        routine outputs "invalid", then do another iteration of Step 1.
  //   //
  //   // More concisely, what these points mean is to use X as a compressed public key.
     val prime = SecP256K1Curve.q
     if (x.compareTo(prime) >= 0)  // Cannot have point co-ordinates larger than this as everything takes place modulo Q.
       BigInt(0)
     else
  //   // Compressed keys require you to know an extra bit of data about the y-coord as there are
  //   // two possibilities. So it's encoded in the recId.
       val r:ECPoint = decompressKey(x, (recId & 1) == 1)
  //   //   1.4. If nR != point at infinity, then do another iteration of Step 1 (callers
  //   //        responsibility).
  //   if (!R.multiply(n).isInfinity) return null
  //   //   1.5. Compute e from M using Steps 2 and 3 of ECDSA signature verification.
       val e = BigInt(1, message)
  //   //   1.6. For k from 1 to 2 do the following.   (loop is outside this function via
  //   //        iterating recId)
  //   //   1.6.1. Compute a candidate public key as:
  //   //               Q = mi(r) * (sR - eG)
  //   // Where mi(x) is the modular multiplicative inverse. We transform this into the following:
  //   //               Q = (mi(r) * s ** R) + (mi(r) * -e ** G)
  //   // Where -e is the modular additive inverse of e, that is z such that z + e = 0 (mod n).
  //   // In the above equation ** is point multiplication and + is point addition (the EC group
  //   // operator).
  //   // We can find the additive inverse by subtracting e from zero then taking the mod. For
  //   // example the additive inverse of 3 modulo 11 is 8 because 3 + 8 mod 11 = 0, and
  //   // -3 mod 11 = 8.
       val eInv = (BigInt(0) - e) % n
       val rInv = sig.r.modInverse(n)
       val srInv = (rInv * sig.s) % n
       val eInvrInv = (rInv * eInv) % n
       val q = ECAlgorithms.sumOfTwoMultiplies(CURVE.getG, eInvrInv.bigInteger, r, srInv.bigInteger)
       val qBytes = q.getEncoded(false)
       BigInt(1, qBytes.tail)
     end if
  end recoverFromSignature

  private def decompressKey(xBN: BigInt, yBit: Boolean): ECPoint =
    val x9 = new X9IntegerConverter
    val compEnc = x9.integerToBytes(xBN.bigInteger, 1 + x9.getByteLength(CURVE.getCurve))
    compEnc(0) = (if (yBit) 0x03 else 0x02).toByte
    CURVE.getCurve.decodePoint(compEnc)
  end decompressKey

  @throws[SignatureException]
  def signedMessageToKey(message: Array[Byte], signatureData: Sign.SignatureData): BigInt =
    signedMessageHashToKey(Hash.sha3(message), signatureData)


  @throws[SignatureException]
  def signedPrefixedMessageToKey(message: Array[Byte], signatureData: Sign.SignatureData): BigInt =
    signedMessageHashToKey(ethereumMessageHash(message), signatureData)


  @throws[SignatureException]
  def signedMessageHashToKey(messageHash: Array[Byte], signatureData: Sign.SignatureData): BigInt =

    val r = signatureData.r
    val s = signatureData.s
    verifyPrecondition( r.length == 32, "r must be 32 bytes")
    verifyPrecondition( s.length == 32, "s must be 32 bytes")
    val header = signatureData.v.head & 0xFF
    // The header byte: 0x1B = first key with even y, 0x1C = first key with odd y,
    //                  0x1D = second key with even y, 0x1E = second key with odd y
    if (header < 27 || header > 34) throw new SignatureException("Header byte out of range: " + header)
    val sig =  ECDSASignature(BigInt(1, signatureData.r), BigInt(1, signatureData.s))
    val recId = header - 27
    val key = recoverFromSignature(recId, sig, messageHash)
    if (key == null) throw new SignatureException("Could not recover public key from signature")
    key
  end signedMessageHashToKey


  def getRecId(signatureData: Sign.SignatureData, chainId: Long): Int =

    val v = Numeric.toBigInt(signatureData.v)
    val lowerRealV = BigInt(Transaction.LOWER_REAL_V)
    val lowerRealVPlus1 = BigInt(Transaction.LOWER_REAL_V + 1)
    val chainIdInc = BigInt(Transaction.CHAIN_ID_INC)
    val result =
      if (v == lowerRealV || v == lowerRealVPlus1) v - lowerRealV
      else if (v.compareTo(chainIdInc) > 0) v - (BigInt(chainId) * BigInt(2)) + chainIdInc
      else throw new RuntimeException(String.format("Unsupported format exception", v))
    result.intValue
  end getRecId


  def getVFromRecId(recId: Int): Array[Byte] = Array[Byte]((Transaction.LOWER_REAL_V + recId).toByte)

  def publicKeyFromPrivate(privKey: BigInt): BigInt =
    val point = publicPointFromPrivate(privKey)
    val encoded = point.getEncoded(false)
    BigInt(1, encoded.tail) // remove prefix
  end publicKeyFromPrivate

  def publicPointFromPrivate(privKey: BigInt): ECPoint =
    val privKeyV = 
      if (privKey.bitLength > CURVE.getN.bitLength) privKey.bigInteger.mod(CURVE.getN) 
      else privKey.bigInteger
    new FixedPointCombMultiplier().multiply(CURVE.getG, privKeyV)
  end publicPointFromPrivate

  def publicFromPoint(bits: Array[Byte]): BigInt = BigInt(1, bits.tail)

end Sign
