package org.web3s.crypto

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.utils.Numeric
import org.web3s.crypto.SampleKeys

class WalletTest extends AnyFunSuite:



//  test("WalletFile"){
//
//    val AES_128_CTR =
//      "{\n"
//        + "    \"crypto\" : {\n"
//        + "        \"cipher\" : \"aes-128-ctr\",\n"
//        + "        \"cipherparams\" : {\n"
//        + "            \"iv\" : \"02ebc768684e5576900376114625ee6f\"\n"
//        + "        },\n"
//        + "        \"ciphertext\" : \"7ad5c9dd2c95f34a92ebb86740b92103a5d1cc4c2eabf3b9a59e1f83f3181216\",\n"
//        + "        \"kdf\" : \"pbkdf2\",\n"
//        + "        \"kdfparams\" : {\n"
//        + "            \"c\" : 262144,\n"
//        + "            \"dklen\" : 32,\n"
//        + "            \"prf\" : \"hmac-sha256\",\n"
//        + "            \"salt\" : \"0e4cf3893b25bb81efaae565728b5b7cde6a84e224cbf9aed3d69a31c981b702\"\n"
//        + "        },\n"
//        + "        \"mac\" : \"2b29e4641ec17f4dc8b86fc8592090b50109b372529c30b001d4d96249edaf62\"\n"
//        + "    },\n"
//        + "    \"id\" : \"af0451b4-6020-4ef0-91ec-794a5a965b01\",\n"
//        + "    \"version\" : 3\n"
//        + "}";
//
//    val walletFile1 = parse(AES_128_CTR).as[Wallet.WalletFile]
//
//    val walletFile2 = Wallet.WalletFile(
//      crypto = Wallet.Crypto(
//        cipher = "aes-128-ctr",
//        ciphertext = "7ad5c9dd2c95f34a92ebb86740b92103a5d1cc4c2eabf3b9a59e1f83f3181216",
//        cipherparams = Wallet.CipherParams("02ebc768684e5576900376114625ee6f"),
//        kdf = "pbkdf2",
//        kdfparams =
//          Wallet.Aes128CtrKdfParams(
//            c = 32 ,
//            dklen = 262144,
//            prf = "hmac-sha256",
//            salt ="0e4cf3893b25bb81efaae565728b5b7cde6a84e224cbf9aed3d69a31c981b702"
//          ),
//        mac = "2b29e4641ec17f4dc8b86fc8592090b50109b372529c30b001d4d96249edaf62"
//      ),
//      version = 3,
//      id = "af0451b4-6020-4ef0-91ec-794a5a965b01"
//    )
//
//    assert(walletFile1 === walletFile2)
//  }

  private def testCreate(walletFile: Wallet.WalletFile): Unit =
    assert(walletFile.address == SampleKeys.ADDRESS_NO_PREFIX)

  test("CreateStandard"){
    testCreate(Wallet.createStandard(SampleKeys.PASSWORD, SampleKeys.KEY_PAIR))
  }

  test("CreateLight") {
    testCreate(Wallet.createLight(SampleKeys.PASSWORD, SampleKeys.KEY_PAIR))
  }

  private def testEncryptDecrypt(walletFile: Wallet.WalletFile): Unit =
    assert(Wallet.decrypt(SampleKeys.PASSWORD, walletFile) == SampleKeys.KEY_PAIR)

  test("EncryptDecryptStandard"){
    testEncryptDecrypt(Wallet.createStandard(SampleKeys.PASSWORD, SampleKeys.KEY_PAIR))
  }

  test("EncryptDecryptLight"){
    testEncryptDecrypt(Wallet.createLight(SampleKeys.PASSWORD, SampleKeys.KEY_PAIR))
  }

  test("GenerateRandomBytes"){
    assert(Wallet.generateRandomBytes(0) sameElements Array.emptyByteArray)
    assert(Wallet.generateRandomBytes(10).length == 10)
  }

//  test("GenerateBip39Wallets") {
//    val wallet = Wallet.generateBip39Wallet(SampleKeys.PASSWORD, tempDir);
//    val seed = MnemonicUtils.generateSeed(wallet.getMnemonic(), SampleKeys.PASSWORD);
//    val credentials = Credentials.create(ECKeyPair.create(sha256(seed)));
//
//    assert(credentials == WalletUtils.loadBip39Credentials(SampleKeys.PASSWORD, wallet));
//}
//
//@Test
//public void testGenerateBip39WalletFromMnemonic() throws Exception {
//  Bip39Wallet wallet =
//    WalletUtils.generateBip39WalletFromMnemonic(PASSWORD, MNEMONIC, tempDir);
//  byte[] seed = MnemonicUtils.generateSeed(wallet.getMnemonic(), PASSWORD);
//  Credentials credentials = Credentials.create(ECKeyPair.create(sha256(seed)));
//
//  assertEquals(credentials, WalletUtils.loadBip39Credentials(PASSWORD, wallet.getMnemonic()));
//}
private val PASSWORD = "Insecure Pa55w0rd"
private val SECRET = "a392604efc2fad9c0b3da43b5f698a2e3f270f170d859912be0d54742275c5f6"
private val AES_128_CTR = """{ "crypto" : {"cipher" : "aes-128-ctr",        "cipherparams" : {            "iv" : "02ebc768684e5576900376114625ee6f"        },        "ciphertext" : "7ad5c9dd2c95f34a92ebb86740b92103a5d1cc4c2eabf3b9a59e1f83f3181216",        "kdf" : "pbkdf2",        "kdfparams" : {            "c" : 262144,            "dklen" : 32,            "prf" : "hmac-sha256",            "salt" : "0e4cf3893b25bb81efaae565728b5b7cde6a84e224cbf9aed3d69a31c981b702"        },        "mac" : "2b29e4641ec17f4dc8b86fc8592090b50109b372529c30b001d4d96249edaf62"    },    "id" : "af0451b4-6020-4ef0-91ec-794a5a965b01",    "version" : 3}"""
private val SCRYPT = """{ "crypto" : { "cipher" : "aes-128-ctr",        "cipherparams" : {            "iv" : "3021e1ef4774dfc5b08307f3a4c8df00"        },        "ciphertext" : "4dd29ba18478b98cf07a8a44167acdf7e04de59777c4b9c139e3d3fa5cb0b931",        "kdf" : "scrypt",        "kdfparams" : {            "dklen" : 32,            "n" : 262144,            "r" : 8,            "p" : 1,            "salt" : "4f9f68c71989eb3887cd947c80b9555fce528f210199d35c35279beb8c2da5ca"        },        "mac" : "7e8f2192767af9be18e7a373c1986d9190fcaa43ad689bbb01a62dbde159338d"    },    "id" : "7654525c-17e0-4df5-94b5-c7fde752c9d2",    "version" : 3}"""

//
//@Test
//@throws[Exception]
//def testDecryptAes128Ctr(): Unit = {
//  val walletFile = load(AES_128_CTR)
//  val ecKeyPair = Wallet.decrypt(PASSWORD, walletFile)
//  assert(Numeric.toHexStringNoPrefix(ecKeyPair.getPrivateKey) == SECRET)
//}
//
//@Test
//@throws[Exception]
//def testDecryptScrypt(): Unit = {
//  val walletFile = load(SCRYPT)
//  val ecKeyPair = Wallet.decrypt(PASSWORD, walletFile)
//  assertEquals(Numeric.toHexStringNoPrefix(ecKeyPair.getPrivateKey), SECRET)
//}

//@throws[IOException]
//private def load(source: String) = {
//  val objectMapper = new ObjectMapper
//  objectMapper.readValue(source, classOf[WalletFile])
//}
//
//
//
