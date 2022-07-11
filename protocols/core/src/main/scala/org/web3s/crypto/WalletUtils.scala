//package org.web3s.crypto
//
//import org.web3s.utils.Numeric
//import org.web3s.crypto.Credentials
//import org.web3s.crypto.exception.CipherException
//import org.web3s.crypto.Keys._
//import org.web3s.crypto.Bip32ECKeyPair.HARDENED_BIT
//
//import java.security.InvalidAlgorithmParameterException
//import java.security.NoSuchAlgorithmException
//import java.security.NoSuchProviderException
//
//object WalletUtils:
//
//  private val secureRandom = SecureRandomUtils.secureRandom
//
//  @throws[NoSuchAlgorithmException]
//  @throws[NoSuchProviderException]
//  @throws[InvalidAlgorithmParameterException]
//  @throws[CipherException]
//  def generateFullNewWalletFile(password: String, destinationDirectory: File): String = generateNewWalletFile(password, destinationDirectory, true)
//
//  @throws[NoSuchAlgorithmException]
//  @throws[NoSuchProviderException]
//  @throws[InvalidAlgorithmParameterException]
//  @throws[CipherException]
//  def generateLightNewWalletFile(password: String, destinationDirectory: File): String = generateNewWalletFile(password, destinationDirectory, false)
//
//  @throws[CipherException]
//  @throws[InvalidAlgorithmParameterException]
//  @throws[NoSuchAlgorithmException]
//  @throws[NoSuchProviderException]
//  def generateNewWalletFile(password: String, destinationDirectory: File): String = generateFullNewWalletFile(password, destinationDirectory)
//
//  @throws[CipherException]
//  @throws[InvalidAlgorithmParameterException]
//  @throws[NoSuchAlgorithmException]
//  @throws[NoSuchProviderException]
//  def generateNewWalletFile(password: String, destinationDirectory: File, useFullScrypt: Boolean): String =
//    generateWalletFile(password, Keys.createEcKeyPair, destinationDirectory, useFullScrypt)
//
//  @throws[CipherException]
//  def generateWalletFile(password: String, ecKeyPair: ECKeyPair, destinationDirectory: File, useFullScrypt: Boolean): String = {
//    val walletFile =
//      if useFullScrypt
//        then Wallet.createStandard(password, ecKeyPair)
//        else Wallet.createLight(password, ecKeyPair)
//    val fileName = getWalletFileName(walletFile)
//    val destination = new File(destinationDirectory, fileName)
//
//    fileName
//  }
//
//  /**
//   * Generates a BIP-39 compatible Ethereum wallet. The private key for the wallet can be
//   * calculated using following algorithm:
//   *
//   * <pre>
//   * Key = SHA-256(BIP_39_SEED(mnemonic, password))
//   * </pre>
//   *
//   * @param password             Will be used for both wallet encryption and passphrase for BIP-39 seed
//   * @param destinationDirectory The directory containing the wallet
//   * @return A BIP-39 compatible Ethereum wallet
//   * @throws CipherException if the underlying cipher is not available
//   * @throws IOException     if the destination cannot be written to
//   */
//  @throws[CipherException]
//  def generateBip39Wallet(password: String, destinationDirectory: File): Bip39Wallet = {
//    val initialEntropy = new Array[Byte](16)
//    secureRandom.nextBytes(initialEntropy)
//    val mnemonic = MnemonicUtils.generateMnemonic(initialEntropy)
//    val seed = MnemonicUtils.generateSeed(mnemonic, password)
//    val privateKey = ECKeyPair.create(sha256(seed))
//    val walletFile = generateWalletFile(password, privateKey, destinationDirectory, false)
//    Bip39Wallet(walletFile, mnemonic)
//  }
//
//  /**
//   * Generates a BIP-39 compatible Ethereum wallet using a mnemonic passed as argument.
//   *
//   * @param password             Will be used for both wallet encryption and passphrase for BIP-39 seed
//   * @param mnemonic             The mnemonic that will be used to generate the seed
//   * @param destinationDirectory The directory containing the wallet
//   * @return A BIP-39 compatible Ethereum wallet
//   * @throws CipherException if the underlying cipher is not available
//   * @throws IOException     if the destination cannot be written to
//   */
//  @throws[CipherException]
//  def generateBip39WalletFromMnemonic(password: String, mnemonic: String, destinationDirectory: File): Bip39Wallet = {
//    val seed = MnemonicUtils.generateSeed(mnemonic, password)
//    val privateKey = ECKeyPair.create(sha256(seed))
//    val walletFile = generateWalletFile(password, privateKey, destinationDirectory, false)
//    Bip39Wallet(walletFile, mnemonic)
//  }
//
//  @throws[CipherException]
//  def loadCredentials(password: String, source: String): Credentials = loadCredentials(password, new File(source))
//
//  // @throws[CipherException]
//  // def loadCredentials(password: String, source: File): Credentials = {
//  //   val walletFile = objectMapper.readValue(source, classOf[WalletFile])
//  //   Credentials.create(Wallet.decrypt(password, walletFile))
//  // }
//
//  def loadBip39Credentials(password: String, mnemonic: String): Credentials = {
//    val seed = MnemonicUtils.generateSeed(mnemonic, password)
//    Credentials.create(ECKeyPair.create(sha256(seed)))
//  }
//
//
//  private def getWalletFileName(walletFile: WalletFile) = {
//    val format = DateTimeFormatter.ofPattern("'UTC--'yyyy-MM-dd'T'HH-mm-ss.nVV'--'")
//    val now = ZonedDateTime.now(ZoneOffset.UTC)
//    now.format(format) + walletFile.getAddress + ".json"
//  }
//
//  def defaultKeyDirectory: String = defaultKeyDirectory(System.getProperty("os.name"))
//
//  private def defaultKeyDirectory(osName1: String) = {
//    val osName = osName1.toLowerCase
//    if (osName.startsWith("mac")) String.format("%s%sLibrary%sEthereum", System.getProperty("user.home"), File.separator, File.separator)
//    else if (osName.startsWith("win")) String.format("%s%sEthereum", System.getenv("APPDATA"), File.separator)
//    else String.format("%s%s.ethereum", System.getProperty("user.home"), File.separator)
//  }
//
//  def testnetKeyDirectory: String = "%s%stestnet%skeystore".format(defaultKeyDirectory, File.separator, File.separator)
//
//  def mainnetKeyDirectory: String = "%s%skeystore".format(defaultKeyDirectory, File.separator)
//
//  def rinkebyKeyDirectory: String = "%s%srinkeby%skeystore".format(defaultKeyDirectory, File.separator, File.separator)
//
//  def isValidPrivateKey(privateKey: String): Boolean =
//    Numeric.cleanHexPrefix(privateKey).length == PRIVATE_KEY_LENGTH_IN_HEX
//
//  def isValidAddress(input: String): Boolean =
//    isValidAddress(input, ADDRESS_LENGTH_IN_HEX)
//
//  def isValidAddress(input: String, addressLength: Int): Boolean =
//    val cleanInput = Numeric.cleanHexPrefix(input)
//    Try(Numeric.toBigIntNoPrefix(cleanInput)).toOption.exist(_ => cleanInput.length == addressLength)
//
//
//    /**
//     * Generates a BIP-44 compatible Ethereum wallet on top of BIP-39 generated seed.
//     *
//     * @param password             Will be used for both wallet encryption and passphrase for BIP-39 seed
//     * @param destinationDirectory The directory containing the wallet
//     * @param testNet              should use the testNet derive path
//     * @return A BIP-39 compatible Ethereum wallet
//     * @throws CipherException if the underlying cipher is not available
//     * @throws IOException     if the destination cannot be written to
//     */
//  @throws[CipherException]
//  def generateBip44Wallet(password: String, destinationDirectory: File, testNet: Boolean = false): Bip39Wallet = {
//    val initialEntropy = new Array[Byte](16)
//    SecureRandomUtils.secureRandom.nextBytes(initialEntropy)
//    val mnemonic = MnemonicUtils.generateMnemonic(initialEntropy)
//    val seed = MnemonicUtils.generateSeed(mnemonic, null)
//    val masterKeypair = Bip32ECKeyPair.generateKeyPair(seed)
//    val bip44Keypair = generateBip44KeyPair(masterKeypair, testNet)
//    val walletFile = generateWalletFile(password, bip44Keypair, destinationDirectory, false)
//    Bip39Wallet(walletFile, mnemonic)
//  }
//
//  def generateBip44KeyPair(master: Bip32ECKeyPair, testNet: Boolean = false): Bip32ECKeyPair =
//    val path =
//      if testNet
//      then Array(44 | HARDENED_BIT, 0 | HARDENED_BIT, 0 | HARDENED_BIT, 0) // /m/44'/0'/0
//      else Array(44 | HARDENED_BIT, 60 | HARDENED_BIT, 0 | HARDENED_BIT, 0, 0) // m/44'/60'/0'/0/0
//    Bip32ECKeyPair.deriveKeyPair(master, path)
//
//  def loadBip44Credentials(password: String, mnemonic: String, testNet: Boolean = false):Credentials =
//    val seed = MnemonicUtils.generateSeed(mnemonic, password)
//    val masterKeypair = Bip32ECKeyPair.generateKeyPair(seed)
//    val bip44Keypair = generateBip44KeyPair(masterKeypair, testNet)
//    Credentials.create(bip44Keypair)
//
//
//end WalletUtils
//
