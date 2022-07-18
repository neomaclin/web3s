package org.web3s.crypto

import cats.{Eq, Show}
import org.bouncycastle.crypto.digests.SHA256Digest
import org.bouncycastle.crypto.generators.{PKCS5S2ParametersGenerator, SCrypt}
import org.bouncycastle.crypto.params.KeyParameter
import org.web3s.crypto.Keys
import org.web3s.utils.Numeric
import org.web3s.crypto.Credentials
import org.web3s.crypto.exception.CipherException
import org.web3s.crypto.Keys.*
import org.web3s.crypto.Bip32ECKeyPair.HARDENED_BIT
import org.web3s.crypto.SecureRandomUtils.secureRandom
import org.web3s.crypto.Wallet.KdfParams

import java.security.InvalidAlgorithmParameterException
import java.security.NoSuchAlgorithmException
import java.security.NoSuchProviderException
import java.nio.charset.StandardCharsets.UTF_8
import java.security.{InvalidAlgorithmParameterException, InvalidKeyException, NoSuchAlgorithmException}
import java.util.UUID
import java.util.Arrays
import javax.crypto.{BadPaddingException, Cipher, IllegalBlockSizeException, NoSuchPaddingException}
import javax.crypto.spec.{IvParameterSpec, SecretKeySpec}


object Wallet:

  import cats.syntax._
  import cats.syntax.functor._
  import io.circe.{Decoder, Encoder}, io.circe.generic.semiauto._
  import io.circe.syntax._
  import cats.implicits._, cats._, cats.derived._

  private val N_LIGHT = 1 << 12
  private val P_LIGHT = 6

  private val N_STANDARD = 1 << 18
  private val P_STANDARD = 1

  private val R = 8
  private val DKLEN = 32

  private val CURRENT_VERSION = 3

  private val CIPHER = "aes-128-ctr"
  val AES_128_CTR = "pbkdf2"
  val SCRYPT = "scrypt"

  final case class WalletFile(address: Option[String],
                              crypto: Crypto,
                              id: String,
                              version: Int)

  final case class Crypto(cipher: String,
                          ciphertext: String,
                          cipherparams: CipherParams,
                          kdf: String,
                          kdfparams: KdfParams,
                          mac: String)

  final case class CipherParams(iv: String)

  sealed trait KdfParams:
    def dklen: Int

    def salt: String

  final case class Aes128CtrKdfParams(dklen: Int, c: Int, prf: String, salt: String) extends KdfParams

  final case class ScryptKdfParams(dklen: Int, n: Int, p: Int, r: Int, salt: String) extends KdfParams

  @throws[CipherException]
  private def generateDerivedScryptKey(password: Array[Byte], salt: Array[Byte], n: Int, r: Int, p: Int, dkLen: Int) = SCrypt.generate(password, salt, n, r, p, dkLen)

  @throws[CipherException]
  private def generateAes128CtrDerivedKey(password: Array[Byte], salt: Array[Byte], c: Int, prf: String) = {
    if prf =!= "hmac-sha256" then throw new CipherException("Unsupported prf:" + prf)
    val gen = new PKCS5S2ParametersGenerator(new SHA256Digest)
    gen.init(password, salt, c)
    gen.generateDerivedParameters(256).asInstanceOf[KeyParameter].getKey
  }

  @throws[CipherException]
  private def performCipherOperation(mode: Int, iv: Array[Byte], encryptKey: Array[Byte], text: Array[Byte]) = try {
    val ivParameterSpec = new IvParameterSpec(iv)
    val cipher = Cipher.getInstance("AES/CTR/NoPadding")
    val secretKeySpec = new SecretKeySpec(encryptKey, "AES")
    cipher.init(mode, secretKeySpec, ivParameterSpec)
    cipher.doFinal(text)
  } catch {
    case e@(_: NoSuchPaddingException | _: NoSuchAlgorithmException | _: InvalidAlgorithmParameterException | _: InvalidKeyException | _: BadPaddingException | _: IllegalBlockSizeException) =>
      throw new CipherException("Error performing cipher operation", e)
  }


  def generateRandomBytes(size: Int) =
    val bytes = new Array[Byte](size)
    secureRandom.nextBytes(bytes)
    bytes


  def createWalletFile(
                        ecKeyPair: ECKeyPair,
                        cipherText: Array[Byte],
                        iv: Array[Byte],
                        salt: Array[Byte],
                        mac: Array[Byte],
                        _n: Int,
                        _p: Int): WalletFile =
    WalletFile(
      address = Some(Keys.getAddress(ecKeyPair)),
      crypto = Crypto(
        cipher = CIPHER,
        ciphertext = Numeric.toHexStringNoPrefix(cipherText),
        cipherparams = CipherParams(Numeric.toHexStringNoPrefix(iv)),
        kdf = SCRYPT,
        kdfparams = ScryptKdfParams(
          dklen = DKLEN,
          n = _n,
          p = _p,
          r = R,
          salt = Numeric.toHexStringNoPrefix(salt)
        ),
        mac = Numeric.toHexStringNoPrefix(mac),
      ),
      id = UUID.randomUUID.toString,
      version = CURRENT_VERSION
    )

  private def generateMac(derivedKey: Array[Byte], cipherText: Array[Byte]): Array[Byte] =
    val result = new Array[Byte](16 + cipherText.length)
    System.arraycopy(derivedKey, 16, result, 0, 16)
    System.arraycopy(cipherText, 0, result, 16, cipherText.length)
    org.web3s.crypto.Hash.sha3(result)

  @throws[CipherException]
  def create(password: String, ecKeyPair: ECKeyPair, n: Int, p: Int) =
    val salt = generateRandomBytes(32)
    val derivedKey = generateDerivedScryptKey(password.getBytes(UTF_8), salt, n, R, p, DKLEN)
    val encryptKey = Arrays.copyOfRange(derivedKey, 0, 16)
    val iv = generateRandomBytes(16)
    val privateKeyBytes = Numeric.toBytesPadded(ecKeyPair.privateKey, Keys.PRIVATE_KEY_SIZE)
    val cipherText = performCipherOperation(Cipher.ENCRYPT_MODE, iv, encryptKey, privateKeyBytes)
    val mac = generateMac(derivedKey, cipherText)
    createWalletFile(ecKeyPair, cipherText, iv, salt, mac, n, p)

  @throws[CipherException]
  def createStandard(password: String, ecKeyPair: ECKeyPair): WalletFile = create(password, ecKeyPair, N_STANDARD, P_STANDARD)

  @throws[CipherException]
  def createLight(password: String, ecKeyPair: ECKeyPair): WalletFile = create(password, ecKeyPair, N_LIGHT, P_LIGHT)

  @throws[CipherException]
  def validate(walletFile: WalletFile): Unit =
    val crypto = walletFile.crypto
    if walletFile.version != CURRENT_VERSION then throw new CipherException("Wallet version is not supported")
    if crypto.cipher != CIPHER then throw new CipherException("Wallet cipher is not supported")
    if (crypto.kdf != AES_128_CTR) && (crypto.kdf != SCRYPT) then throw new CipherException("KDF type is not supported")

  @throws[CipherException]
  def decrypt(password: String, walletFile: WalletFile): ECKeyPair =
    validate(walletFile)
    val crypto: Crypto = walletFile.crypto
    val mac: Array[Byte] = Numeric.hexStringToByteArray(crypto.mac)
    val iv: Array[Byte] = Numeric.hexStringToByteArray(crypto.cipherparams.iv)
    val cipherText: Array[Byte] = Numeric.hexStringToByteArray(crypto.ciphertext)
    val derivedKey = crypto.kdfparams match {
      case ScryptKdfParams(dklen: Int, n: Int, p: Int, r: Int, salt: String) =>
        generateDerivedScryptKey(password.getBytes(UTF_8), Numeric.hexStringToByteArray(salt), n, r, p, dklen)
      case Aes128CtrKdfParams(dklen: Int, c: Int, prf: String, salt: String) =>
        generateAes128CtrDerivedKey(password.getBytes(UTF_8), Numeric.hexStringToByteArray(salt), c, prf)
    }

    val derivedMac: Array[Byte] = generateMac(derivedKey, cipherText)
    if !(derivedMac sameElements mac) then throw new CipherException("Invalid password provided")
    val encryptKey: Array[Byte] = Arrays.copyOfRange(derivedKey, 0, 16)
    val privateKey: Array[Byte] = performCipherOperation(Cipher.DECRYPT_MODE, iv, encryptKey, cipherText)
    ECKeyPair.create(privateKey)

  given Decoder[CipherParams] = deriveDecoder[CipherParams]

  given Encoder[CipherParams] = deriveEncoder[CipherParams]


  given Eq[CipherParams] = semiauto.eq

  given Decoder[Aes128CtrKdfParams] = deriveDecoder[Aes128CtrKdfParams]

  given Encoder[Aes128CtrKdfParams] = deriveEncoder[Aes128CtrKdfParams]


  given Eq[Aes128CtrKdfParams] = semiauto.eq

  given Decoder[ScryptKdfParams] = deriveDecoder[ScryptKdfParams]

  given Encoder[ScryptKdfParams] = deriveEncoder[ScryptKdfParams]


  given Eq[ScryptKdfParams] = semiauto.eq

  given Decoder[KdfParams] = List[Decoder[KdfParams]](
    Decoder[Aes128CtrKdfParams].widen,
    Decoder[ScryptKdfParams].widen,
  ).reduceLeft(_ or _)

  given Encoder[KdfParams] = Encoder.instance {
    case a: Aes128CtrKdfParams => a.asJson
    case s: ScryptKdfParams => s.asJson
  }

  given Eq[KdfParams] = semiauto.eq

  given Decoder[Crypto] = deriveDecoder[Crypto]

  given Encoder[Crypto] = deriveEncoder[Crypto]

  given Eq[Crypto] = semiauto.eq

  given Decoder[WalletFile] = deriveDecoder[WalletFile]

  given Encoder[WalletFile] = deriveEncoder[WalletFile]

  given Eq[WalletFile] = semiauto.eq
