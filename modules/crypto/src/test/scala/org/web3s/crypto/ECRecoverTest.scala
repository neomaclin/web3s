
package org.web3s.crypto

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.utils.Numeric

class ECRecoverTest extends AnyFunSuite :

  val PERSONAL_MESSAGE_PREFIX = "\u0019Ethereum Signed Message:\n"
  
  test("RecoverAddressFromSignature") {
    val signature = "0x2c6401216c9031b9a6fb8cbfccab4fcec6c951cdf40e2320108d1856eb532250576865fbcd452bcdc4c57321b619ed7a9cfd38bd973c3e1e0243ac2777fe9d5b1b"
    val address = "0x31b26e43651e9371c88af3d36c14cfd938baf4fd"
    val message = "v0G9u7huK4mJb2K1"
    val prefix = PERSONAL_MESSAGE_PREFIX + message.length
    val msgHash = Hash.sha3((prefix + message).getBytes)
    val signatureBytes = Numeric.hexStringToByteArray(signature)
    var v = signatureBytes(64)
    v = if (v < 27.toByte) (v + 27).toByte else v
    val sd = Sign.SignatureData(Array[Byte](v), signatureBytes.slice(0, 32), signatureBytes.slice(32, 64))
    val addressRecovered =
      (0 until 4).view.map { i =>
        "0x" + Keys.getAddress(
          Sign.recoverFromSignature(i.toByte, ECDSASignature(BigInt(1, sd.r), BigInt(1, sd.s)), msgHash)
        )
      }.find(_ == address)

    assert(addressRecovered.nonEmpty)
  }
end ECRecoverTest
