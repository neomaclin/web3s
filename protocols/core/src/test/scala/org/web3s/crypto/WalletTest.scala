package org.web3s.crypto

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.utils.Numeric
import org.web3s.crypto.{Bip32Test, Base58, SampleKeys}

class WalletTest extends AnyFunSuite :

  import cats.syntax.EqSyntax
  import io.circe._, io.circe.parser._


  private val SECRET = "a392604efc2fad9c0b3da43b5f698a2e3f270f170d859912be0d54742275c5f6"
  private val AES_128_CTR = """{ "crypto" : {"cipher" : "aes-128-ctr",        "cipherparams" : {            "iv" : "02ebc768684e5576900376114625ee6f"        },        "ciphertext" : "7ad5c9dd2c95f34a92ebb86740b92103a5d1cc4c2eabf3b9a59e1f83f3181216",        "kdf" : "pbkdf2",        "kdfparams" : {            "c" : 262144,            "dklen" : 32,            "prf" : "hmac-sha256",            "salt" : "0e4cf3893b25bb81efaae565728b5b7cde6a84e224cbf9aed3d69a31c981b702"        },        "mac" : "2b29e4641ec17f4dc8b86fc8592090b50109b372529c30b001d4d96249edaf62"    },    "id" : "af0451b4-6020-4ef0-91ec-794a5a965b01",    "version" : 3}"""
  private val SCRYPT = """{ "crypto" : { "cipher" : "aes-128-ctr",        "cipherparams" : {            "iv" : "3021e1ef4774dfc5b08307f3a4c8df00"        },        "ciphertext" : "4dd29ba18478b98cf07a8a44167acdf7e04de59777c4b9c139e3d3fa5cb0b931",        "kdf" : "scrypt",        "kdfparams" : {            "dklen" : 32,            "n" : 262144,            "r" : 8,            "p" : 1,            "salt" : "4f9f68c71989eb3887cd947c80b9555fce528f210199d35c35279beb8c2da5ca"        },        "mac" : "7e8f2192767af9be18e7a373c1986d9190fcaa43ad689bbb01a62dbde159338d"    },    "id" : "7654525c-17e0-4df5-94b5-c7fde752c9d2",    "version" : 3}"""


  test("WalletFile") {

    val AES_128_CTR =
      "{\n"
        + "    \"crypto\" : {\n"
        + "        \"cipher\" : \"aes-128-ctr\",\n"
        + "        \"cipherparams\" : {\n"
        + "            \"iv\" : \"02ebc768684e5576900376114625ee6f\"\n"
        + "        },\n"
        + "        \"ciphertext\" : \"7ad5c9dd2c95f34a92ebb86740b92103a5d1cc4c2eabf3b9a59e1f83f3181216\",\n"
        + "        \"kdf\" : \"pbkdf2\",\n"
        + "        \"kdfparams\" : {\n"
        + "            \"c\" : 262144,\n"
        + "            \"dklen\" : 32,\n"
        + "            \"prf\" : \"hmac-sha256\",\n"
        + "            \"salt\" : \"0e4cf3893b25bb81efaae565728b5b7cde6a84e224cbf9aed3d69a31c981b702\"\n"
        + "        },\n"
        + "        \"mac\" : \"2b29e4641ec17f4dc8b86fc8592090b50109b372529c30b001d4d96249edaf62\"\n"
        + "    },\n"
        + "    \"id\" : \"af0451b4-6020-4ef0-91ec-794a5a965b01\",\n"
        + "    \"version\" : 3\n"
        + "}";

    val walletFileDecoded = decode[Wallet.WalletFile](AES_128_CTR)

    val walletFile = Wallet.WalletFile(
      address = None,
      crypto = Wallet.Crypto(
        cipher = "aes-128-ctr",
        ciphertext = "7ad5c9dd2c95f34a92ebb86740b92103a5d1cc4c2eabf3b9a59e1f83f3181216",
        cipherparams = Wallet.CipherParams("02ebc768684e5576900376114625ee6f"),
        kdf = "pbkdf2",
        kdfparams =
          Wallet.Aes128CtrKdfParams(
            dklen = 32,
            c = 262144,
            prf = "hmac-sha256",
            salt = "0e4cf3893b25bb81efaae565728b5b7cde6a84e224cbf9aed3d69a31c981b702"
          ),
        mac = "2b29e4641ec17f4dc8b86fc8592090b50109b372529c30b001d4d96249edaf62"
      ),
      version = 3,
      id = "af0451b4-6020-4ef0-91ec-794a5a965b01"
    )

    assert(walletFileDecoded.exists(_ === walletFile))
  }

  private def testCreate(walletFile: Wallet.WalletFile): Unit =
    assert(walletFile.address.contains(SampleKeys.ADDRESS_NO_PREFIX))

  test("CreateStandard") {
    testCreate(Wallet.createStandard(SampleKeys.PASSWORD, SampleKeys.KEY_PAIR))
  }

  test("CreateLight") {
    testCreate(Wallet.createLight(SampleKeys.PASSWORD, SampleKeys.KEY_PAIR))
  }

  private def testEncryptDecrypt(walletFile: Wallet.WalletFile): Unit =
    assert(Wallet.decrypt(SampleKeys.PASSWORD, walletFile) == SampleKeys.KEY_PAIR)

  test("EncryptDecryptStandard") {
    testEncryptDecrypt(Wallet.createStandard(SampleKeys.PASSWORD, SampleKeys.KEY_PAIR))
  }

  test("EncryptDecryptLight") {
    testEncryptDecrypt(Wallet.createLight(SampleKeys.PASSWORD, SampleKeys.KEY_PAIR))
  }

  test("GenerateRandomBytes") {
    assert(Wallet.generateRandomBytes(0) sameElements Array.emptyByteArray)
    assert(Wallet.generateRandomBytes(10).length == 10)
  }


  test("DecryptAes128Ctr") {
    val walletFile = decode[Wallet.WalletFile](AES_128_CTR).toOption.get
    val ecKeyPair = Wallet.decrypt(SampleKeys.PASSWORD, walletFile)
    assert(Numeric.toHexStringNoPrefix(ecKeyPair.privateKey) == SECRET)
  }

  test("DecryptScrypt") {
    val walletFile = decode[Wallet.WalletFile](SCRYPT).toOption.get
    val ecKeyPair = Wallet.decrypt(SampleKeys.PASSWORD, walletFile)
    assert(Numeric.toHexStringNoPrefix(ecKeyPair.privateKey) == SECRET)
  }


  test("generateBip44KeyPair") {
    val mnemonic =
      "spider elbow fossil truck deal circle divert sleep safe report laundry above";
    val seed = MnemonicUtils.generateSeed(mnemonic, None);
    val seedStr = bytesToHex(seed)
    assert(
      "f0d2ab78b96acd147119abad1cd70eb4fec4f0e0a95744cf532e6a09347b08101213b4cbf50eada0eb89cba444525fe28e69707e52aa301c6b47ce1c5ef82eb5" ==
        seedStr)

    val masterKeypair = Bip32ECKeyPair.generateKeyPair(seed);
    assert(
      "xprv9s21ZrQH143K2yA9Cdad5gjqHRC7apVUgEyYq5jXeXigDZ3PfEnps44tJprtMXr7PZivEsin6Qrbad7PuiEy4tn5jAEK6A3U46f9KvfRCmD" ==
        Base58.encode(Bip32Test.addChecksum(Bip32Test.serializePrivate(masterKeypair))))

    val bip44Keypair = WalletUtils.generateBip44KeyPair(masterKeypair)

    assert(
      "xprvA3p5nTrBJcdEvUQAK64rZ4oJTwsTiMg7JQrqNh6JNWe3VUW2tcLb7GW1wj1fNDAoymUTSFERZ2LxPxJNmqoMZPs9y3TMNMuBN8MS9eigoWq" ==
        Base58.encode(Bip32Test.addChecksum(Bip32Test.serializePrivate(bip44Keypair))))

    assert(
      "xpub6GoSByP58zBY8xUdR7brvCk31yhx7pPxfdnSB5VuvrB2NGqBS9eqf4pVo1xev4GEmip5Wuky9KUtJVxq4fvYfFchS6SA6C4cCRyQkLqNNjq" ==
        Base58.encode(Bip32Test.addChecksum(Bip32Test.serializePublic(bip44Keypair))))

    val credentials = WalletUtils.loadBip44Credentials("", mnemonic) // Verify address according to https://iancoleman.io/bip39/
    assert("0xece62451ca8fba33746d6dafd0d0ebdef84778b7" == credentials.address.toLowerCase)
  }

  test("generateBip44KeyPairTestNet") {
    val mnemonic =
      "spider elbow fossil truck deal circle divert sleep safe report laundry above";
    val seed = MnemonicUtils.generateSeed(mnemonic, None);
    val seedStr = bytesToHex(seed);
    assert(
      "f0d2ab78b96acd147119abad1cd70eb4fec4f0e0a95744cf532e6a09347b08101213b4cbf50eada0eb89cba444525fe28e69707e52aa301c6b47ce1c5ef82eb5" ==
        seedStr)

    val masterKeypair = Bip32ECKeyPair.generateKeyPair(seed)
    assert(
      "xprv9s21ZrQH143K2yA9Cdad5gjqHRC7apVUgEyYq5jXeXigDZ3PfEnps44tJprtMXr7PZivEsin6Qrbad7PuiEy4tn5jAEK6A3U46f9KvfRCmD" ==
        Base58.encode(Bip32Test.addChecksum(Bip32Test.serializePrivate(masterKeypair))))

    val bip44Keypair = WalletUtils.generateBip44KeyPair(masterKeypair, true);

    assert(
      "xprv9zhLxq63By3SX5hAMKnxjGy7L18bnn7GzDQv53eYYqeRX9M82riC1dqovamttwFpk2ZkDQxgcikBQzs1DTu2KShJJqnqgx83EftUB3k39uc" ==
        Base58.encode(Bip32Test.addChecksum(Bip32Test.serializePrivate(bip44Keypair))))
    assert(
      "xpub6DghNLcw2LbjjZmdTMKy6Quqt2y6CEq8MSLWsS4A7BBQPwgGaQ2SZSAHmsrqBVxLegjW2mBfcvDBhpeEqCmucTTPJiNLHQkiDuKwHs9gEtk" ==
        Base58.encode(Bip32Test.addChecksum(Bip32Test.serializePublic(bip44Keypair))))
  }

  private def bytesToHex(bytes: Array[Byte]) = {
    val HEX_CHARS = "0123456789abcdef".toCharArray
    val chars = new Array[Char](2 * bytes.length)
    bytes.zipWithIndex.foreach { case (byte, i) =>
      chars(2 * i) = HEX_CHARS((byte & 0xF0) >>> 4)
      chars(2 * i + 1) = HEX_CHARS(byte & 0x0F)
    }
    String(chars)
  }


