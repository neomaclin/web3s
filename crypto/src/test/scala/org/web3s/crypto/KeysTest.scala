package org.web3s.crypto

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.crypto.Keys.{PRIVATE_KEY_SIZE, PUBLIC_KEY_SIZE}
import org.web3s.utils.Numeric

class KeysTest extends AnyFunSuite :

  val privateKey: Array[Byte] = Numeric.hexStringToByteArray(SampleKeys.PRIVATE_KEY_STRING)
  val publicKey: Array[Byte] = Numeric.hexStringToByteArray(SampleKeys.PUBLIC_KEY_STRING)

  val ENCODED: Array[Byte] = new Array[Byte](privateKey.length + publicKey.length)

  Array.copy(privateKey, 0, ENCODED, 0, PRIVATE_KEY_SIZE)
  Array.copy(publicKey, 0, ENCODED, PRIVATE_KEY_SIZE, PUBLIC_KEY_SIZE)

  test("CreateSecp256k1KeyPair") {
    val keyPair = Keys.createSecp256k1KeyPair
    val privateKey = keyPair.getPrivate
    val publicKey = keyPair.getPublic
    assert(privateKey.getEncoded.length == 144)
    assert(publicKey.getEncoded.length == 88)
  }


  test("CreateEcKeyPair") {
    val ecKeyPair = Keys.createEcKeyPair
    assert(ecKeyPair.publicKey.signum == 1)
    assert(ecKeyPair.privateKey.signum == 1)
  }

  test("GetAddressString") {
    assert(Keys.getAddress(SampleKeys.PUBLIC_KEY_STRING) == SampleKeys.ADDRESS_NO_PREFIX)
  }

  test("GetAddressZeroPaddedAddress") {
    val publicKey = "0xa1b31be4d58a7ddd24b135db0da56a90fb5382077ae26b250e1dc9cd6232ce22" + "70f4c995428bc76aa78e522316e95d7834d725efc9ca754d043233af6ca90113"
    assert(Keys.getAddress(publicKey) == "01c52b08330e05d731e38c856c1043288f7d9744")
  }

  test("GetAddressBigInteger") {
    assert(Keys.getAddress(SampleKeys.PUBLIC_KEY) == SampleKeys.ADDRESS_NO_PREFIX)
  }

  test("GetAddressSmallPublicKey")  {
    val address = Keys.getAddress(Numeric.toBytesPadded(BigInt(0x1234), Keys.PUBLIC_KEY_SIZE))
    val expected = Numeric.toHexStringNoPrefix(address)
    assert(Keys.getAddress("0x1234") == expected)
  }

  test("GetAddressZeroPadded")  {
    val address = Keys.getAddress(Numeric.toBytesPadded(BigInt(0x1234), Keys.PUBLIC_KEY_SIZE))
    val expected = Numeric.toHexStringNoPrefix(address)
    val value = "1234"
    assert(Keys.getAddress("0x" + "0" * (Keys.PUBLIC_KEY_LENGTH_IN_HEX - value.length) + value) == expected)
  }

  test("ToChecksumAddress") { // Test cases as per https://github.com/ethereum/EIPs/blob/master/EIPS/eip-55.md#test-cases
    assert(Keys.toChecksumAddress("0xfb6916095ca1df60bb79ce92ce3ea74c37c5d359") == "0xfB6916095ca1df60bB79Ce92cE3Ea74c37c5d359")
    // All uppercase
    assert(Keys.toChecksumAddress("0x52908400098527886E0F7030069857D2E4169EE7")  ==  "0x52908400098527886E0F7030069857D2E4169EE7")
    assert(Keys.toChecksumAddress("0x8617E340B3D01FA5F11F306F4090FD50E238070D")  ==  "0x8617E340B3D01FA5F11F306F4090FD50E238070D")
    // All lowercase
    assert(Keys.toChecksumAddress("0xde709f2102306220921060314715629080e2fb77")  ==  "0xde709f2102306220921060314715629080e2fb77")
    assert(Keys.toChecksumAddress("0x27b1fdb04752bbc536007a920d24acb045561c26")  ==  "0x27b1fdb04752bbc536007a920d24acb045561c26")
    // Normal
    assert(Keys.toChecksumAddress("0x5aAeb6053F3E94C9b9A09f33669435E7Ef1BeAed")  ==  "0x5aAeb6053F3E94C9b9A09f33669435E7Ef1BeAed")
    assert(Keys.toChecksumAddress("0xfB6916095ca1df60bB79Ce92cE3Ea74c37c5d359")  ==  "0xfB6916095ca1df60bB79Ce92cE3Ea74c37c5d359")
    assert(Keys.toChecksumAddress("0xdbF03B407c01E7cD3CBea99509d93f8DDDC8C6FB")  ==  "0xdbF03B407c01E7cD3CBea99509d93f8DDDC8C6FB")
    assert(Keys.toChecksumAddress("0xD1220A0cf47c7B9Be7A2E6BA89F429762e7b9aDb")  ==  "0xD1220A0cf47c7B9Be7A2E6BA89F429762e7b9aDb")
  }

  test("SerializeECKey") {
    assert(Keys.serialize(SampleKeys.KEY_PAIR) sameElements ENCODED)
  }

  test("DeserializeECKey") {
    assert(Keys.deserialize(ENCODED) == SampleKeys.KEY_PAIR)
  }

  test("DeserializeInvalidKey") {
    assertThrows[RuntimeException] {
      Keys.deserialize(new Array[Byte](0))
    }
  }

end KeysTest
