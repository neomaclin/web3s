
package org.web3s.crypto

import cats.effect.Concurrent
import cats.syntax.functor._
import org.web3s.utils.Numeric
import org.web3s.crypto.Hash
import org.web3s.crypto.Credentials
import org.web3s.crypto.MnemonicUtils
import org.web3s.crypto.exception.CipherException
import org.web3s.crypto.Keys.*
import org.web3s.crypto.Bip32ECKeyPair.HARDENED_BIT

import java.security.InvalidAlgorithmParameterException
import java.security.NoSuchAlgorithmException
import java.security.NoSuchProviderException
import java.time.format.DateTimeFormatter
import java.time.{ZonedDateTime, ZoneId, ZoneOffset}
import fs2.{Stream, hash, text}
import fs2.io.file.{Files, Path}

import scala.util.Try

object WalletUtils:

  import io.circe._
  import io.circe.syntax._
  import io.circe.parser._

  private val secureRandom = SecureRandomUtils.secureRandom

  def generateFullNewWalletFile[F[_] : Files : Concurrent](password: String, destinationDirectory: Path): F[Path] =
    generateNewWalletFile(password, destinationDirectory, true)

  def generateLightNewWalletFile[F[_] : Files : Concurrent](password: String, destinationDirectory: Path): F[Path] =
    generateNewWalletFile(password, destinationDirectory, false)

  def generateNewWalletFile[F[_] : Files : Concurrent](password: String, destinationDirectory: Path): F[Path] =
    generateFullNewWalletFile(password, destinationDirectory)

  def generateNewWalletFile[F[_] : Files : Concurrent](password: String,
                                                       destinationDirectory: Path,
                                                       useFullScrypt: Boolean): F[Path] =
    generateWalletFile(password, Keys.createEcKeyPair, destinationDirectory, useFullScrypt)

  def generateWalletFile[F[_] : Files : Concurrent](password: String,
                                                    ecKeyPair: ECKeyPair,
                                                    destinationDirectory: Path,
                                                    useFullScrypt: Boolean): F[Path] =
    val walletFile =
      if useFullScrypt then Wallet.createStandard(password, ecKeyPair)
      else Wallet.createLight(password, ecKeyPair)
    val destination = destinationDirectory / getWalletFileName(walletFile)
    Stream(walletFile)
      .map(_.asJson.noSpaces)
      .through(text.utf8.encode)
      .through(Files[F].writeAll(destination))
      .compile
      .drain
      .as(destination)


  def generateBip39Wallet[F[_] : Files : Concurrent](password: String,
                                                     destinationDirectory: Path): F[Bip39Wallet] =
    val initialEntropy = new Array[Byte](16)
    secureRandom.nextBytes(initialEntropy)
    val mnemonic = MnemonicUtils.generateMnemonic(initialEntropy)
    val seed = MnemonicUtils.generateSeed(mnemonic, Some(password))
    val privateKey = ECKeyPair.create(Hash.sha256(seed))
    generateWalletFile(password, privateKey, destinationDirectory, false)
      .map(path => Bip39Wallet(path.toString, mnemonic))


  def generateBip39WalletFromMnemonic[F[_] : Files : Concurrent](password: String,
                                                                 mnemonic: String,
                                                                 destinationDirectory: Path): F[Bip39Wallet] =
    val seed = MnemonicUtils.generateSeed(mnemonic, Some(password))
    val privateKey = ECKeyPair.create(Hash.sha256(seed))
    generateWalletFile(password, privateKey, destinationDirectory, false)
      .map(path => Bip39Wallet(path.toString, mnemonic))


  def loadCredentials[F[_] : Files : Concurrent](password: String, source: String): F[Option[Credentials]] =
    loadCredentials(password, Path(source))

  def loadCredentials[F[_] : Files : Concurrent](password: String, source: Path): F[Option[Credentials]] =
    Files[F]
      .readAll(source)
      .through(text.utf8.decode)
      .through(text.lines)
      .map(decode[Wallet.WalletFile])
      .map(_.map(walletFile => Credentials.create(Wallet.decrypt(password, walletFile))))
      .flatMap(Stream.fromEither(_))
      .compile
      .last


  def loadBip39Credentials(password: String, mnemonic: String): Credentials =
    val seed = MnemonicUtils.generateSeed(mnemonic, Some(password))
    Credentials.create(ECKeyPair.create(Hash.sha256(seed)))


  private def getWalletFileName(walletFile: Wallet.WalletFile): String =
    val format = DateTimeFormatter.ofPattern("'UTC--'yyyy-MM-dd'T'HH-mm-ss.nVV'--'")
    val now = ZonedDateTime.now(ZoneId.ofOffset("UTC", ZoneOffset.UTC))
    walletFile.address.map(now.format(format) + _ + ".json").getOrElse("address-missing.json")

  def defaultKeyDirectory: Path = defaultKeyDirectory(sys.env("os.name"))

  private def defaultKeyDirectory(osName1: String): Path =
    osName1.toLowerCase match
      case osName if osName.startsWith("mac") => Path(sys.env("user.home")) / "Library" / "Ethereum"
      case osName if osName.startsWith("win") => Path(sys.env("APPDATA")) / "Ethereum"
      case _ => Path(sys.env("user.home")) / ".ethereum"


  def testnetKeyDirectory: Path = defaultKeyDirectory / "testnet" / "keystore"

  def mainnetKeyDirectory: Path = defaultKeyDirectory / "keystore"

  def rinkebyKeyDirectory: Path = defaultKeyDirectory / "rinkeby" / "keystore"

  def isValidPrivateKey(privateKey: String): Boolean =
    Numeric.cleanHexPrefix(privateKey).length == PRIVATE_KEY_LENGTH_IN_HEX

  def isValidAddress(input: String): Boolean =
    isValidAddress(input, ADDRESS_LENGTH_IN_HEX)

  def isValidAddress(input: String, addressLength: Int): Boolean =
    val cleanInput = Numeric.cleanHexPrefix(input)
    Try(Numeric.toBigIntNoPrefix(cleanInput)).toOption.exists(_ => cleanInput.length == addressLength)

  def generateBip44KeyPair(master: Bip32ECKeyPair, testNet: Boolean = false): Bip32ECKeyPair =
    val path =
      if testNet
      then Array(44 | HARDENED_BIT, 0 | HARDENED_BIT, 0 | HARDENED_BIT, 0) // /m/44'/0'/0
      else Array(44 | HARDENED_BIT, 60 | HARDENED_BIT, 0 | HARDENED_BIT, 0, 0) // m/44'/60'/0'/0/0
    Bip32ECKeyPair.deriveKeyPair(master, path)

  def loadBip44Credentials(password: String, mnemonic: String, testNet: Boolean = false): Credentials =
    val seed = MnemonicUtils.generateSeed(mnemonic, Some(password))
    val masterKeypair = Bip32ECKeyPair.generateKeyPair(seed)
    val bip44Keypair = generateBip44KeyPair(masterKeypair, testNet)
    Credentials.create(ECKeyPair(bip44Keypair.privateKey.getOrElse(BigInt(0)), bip44Keypair.publicKey))


  def generateBip44Wallet[F[_] : Files : Concurrent](password: String,
                                                     destinationDirectory: Path,
                                                     testNet: Boolean = false): F[Bip39Wallet] =
    val initialEntropy = new Array[Byte](16)
    SecureRandomUtils.secureRandom.nextBytes(initialEntropy)
    val mnemonic = MnemonicUtils.generateMnemonic(initialEntropy)
    val seed = MnemonicUtils.generateSeed(mnemonic, None)
    val masterKeypair = Bip32ECKeyPair.generateKeyPair(seed)
    val bip44Keypair = generateBip44KeyPair(masterKeypair, testNet)
    generateWalletFile(password, bip44Keypair.toECKeyPair, destinationDirectory, false)
      .map(path => Bip39Wallet(path.toString, mnemonic))


