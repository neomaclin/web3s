package org.web3s.crypto

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.utils.Numeric

class SignTest extends AnyFunSuite :
  private val TEST_MESSAGE = "A test message".getBytes

  test("SignMessage") {
    val signatureData = Sign.signPrefixedMessage(TEST_MESSAGE, SampleKeys.KEY_PAIR)
    val expected = Sign.SignatureData(Array[Byte](28.toByte), Numeric.hexStringToByteArray("0x0464eee9e2fe1a10ffe48c78b80de1ed8dcf996f3f60955cb2e03cb21903d930"), Numeric.hexStringToByteArray("0x06624da478b3f862582e85b31c6a21c6cae2eee2bd50f55c93c4faad9d9c8d7f"))
    assert(signatureData == expected)
  }

  test("SignedMessageToKey") {
    val signatureData = Sign.signPrefixedMessage(TEST_MESSAGE, SampleKeys.KEY_PAIR)
    val key = Sign.signedPrefixedMessageToKey(TEST_MESSAGE, signatureData)
    assert(key == SampleKeys.PUBLIC_KEY)
  }

  test("PublicKeyFromPrivateKey") {
    assert(Sign.publicKeyFromPrivate(SampleKeys.PRIVATE_KEY) == SampleKeys.PUBLIC_KEY)
  }

  test("InvalidSignature") {
    assertThrows[RuntimeException] {
      Sign.signedMessageToKey(TEST_MESSAGE, Sign.SignatureData(Array[Byte](27.toByte), Array[Byte](1), Array[Byte](0)))
    }
  }

  test("PublicKeyFromPrivatePoint") {
    val point = Sign.publicPointFromPrivate(SampleKeys.PRIVATE_KEY)
    assert(Sign.publicFromPoint(point.getEncoded(false)) == SampleKeys.PUBLIC_KEY)
  }

end SignTest
