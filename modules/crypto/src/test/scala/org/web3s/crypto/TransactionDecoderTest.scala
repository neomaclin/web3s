
package org.web3s.crypto

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.crypto.transaction.*
import org.web3s.utils.Numeric

class TransactionDecoderTest extends AnyFunSuite :
  private def createEip1559RawTransaction = RawTransaction.createEtherTransaction(3L, BigInt(0), BigInt(30000), "0x627306090abab3a6e1400e9345bc60c78a8bef57", BigInt(123), BigInt(5678), BigInt(1100000))

  val nonce = BigInt(0)
  val gasPrice = BigInt(1)
  val gasLimit = BigInt(10)

  test("Decoding") {
    val to = "0x0add5355"
    val value = BigInt(Long.MaxValue)
    val rawTransaction = RawTransaction.createEtherTransaction(nonce, gasPrice, gasLimit, to, value)
    val encodedMessage = Transaction.Encoder.encode(rawTransaction)
    val hexMessage = Numeric.toHexString(encodedMessage)
    val result = Transaction.Decoder.decode(hexMessage)
    //  assertNotNull(result)
    assert(nonce == result.transaction.nonce)
    assert(gasPrice == result.transaction.gasPrice)
    assert(gasLimit == result.transaction.gasLimit)
    assert(to == result.transaction.to)
    assert(value == result.transaction.value)
    assert("" == result.transaction.data)
  }

  test("DecodingSigned") {

    val to = "0x0add5355"
    val value = BigInt(Long.MaxValue)
    val rawTransaction = RawTransaction.createEtherTransaction(nonce, gasPrice, gasLimit, to, value)
    val signedMessage = Transaction.Encoder.signMessage(rawTransaction, SampleKeys.CREDENTIALS)
    val hexMessage = Numeric.toHexString(signedMessage)
    val result = Transaction.Decoder.decode(hexMessage)

    assert(nonce == result.transaction.nonce)
    assert(gasPrice == result.transaction.gasPrice)
    assert(gasLimit == result.transaction.gasLimit)
    assert(to == result.transaction.to)
    assert(value == result.transaction.value)
    assert("" == result.transaction.data)
    assert(result.isInstanceOf[SignedRawTransaction])
    val signedResult = result.asInstanceOf[SignedRawTransaction]
   // assertNotNull(signedResult.getSignatureData)
    val signatureData = signedResult.signatureData
    val encodedTransaction = Transaction.Encoder.encode(rawTransaction)
    val key = Sign.signedMessageToKey(encodedTransaction, signatureData)
    assert(key == SampleKeys.PUBLIC_KEY)
    assert(SampleKeys.ADDRESS == signedResult.from)
    signedResult.verify(SampleKeys.ADDRESS)
    //assertNull(signedResult.getChainId)
  }

  test("DecodingSignedChainId") {

    val to = "0x0add5355"
    val value = BigInt(Long.MaxValue)
    val chainId = 46
    val rawTransaction = RawTransaction.createEtherTransaction(nonce, gasPrice, gasLimit, to, value)
    val signedMessage = Transaction.Encoder.signMessage(rawTransaction, chainId, SampleKeys.CREDENTIALS)
    val hexMessage = Numeric.toHexString(signedMessage)
    val result = Transaction.Decoder.decode(hexMessage)

    assert(nonce == result.transaction.nonce)
    assert(gasPrice == result.transaction.gasPrice)
    assert(gasLimit == result.transaction.gasLimit)
    assert(to == result.transaction.to)
    assert(value == result.transaction.value)
    assert("" == result.transaction.data)
    assert(result.isInstanceOf[SignedRawTransaction])
    val signedResult = result.asInstanceOf[SignedRawTransaction]
    assert(SampleKeys.ADDRESS == signedResult.from)
    signedResult.verify(SampleKeys.ADDRESS)
    assert(chainId == signedResult.chainId.getOrElse(0))
  }

   test("RSize31") {
    val hexTransaction = "0xf883370183419ce09433c98f20dd73d7bb1d533c4aa3371f2b30c6ebde80a45093dc7d00000000000000000000000000000000000000000000000000000000000000351c9fb90996c836fb34b782ee3d6efa9e2c79a75b277c014e353b51b23b00524d2da07435ebebca627a51a863bf590aff911c4746ab8386a0477c8221bb89671a5d58"
    val result = Transaction.Decoder.decode(hexTransaction)
    val signedResult: SignedRawTransaction = result.asInstanceOf[SignedRawTransaction]
    assert("0x1b609b03e2e9b0275a61fa5c69a8f32550285536" == signedResult.from)
  }

  test("Decoding1559") {
    val rawTransaction = createEip1559RawTransaction
    val transaction1559 = rawTransaction.transaction.asInstanceOf[Transaction1559]
    val encodedMessage = Transaction.Encoder.encode(rawTransaction)
    val hexMessage = Numeric.toHexString(encodedMessage)
    val result = Transaction.Decoder.decode(hexMessage)
    assert(result.transaction.isInstanceOf[Transaction1559])
    val resultTransaction1559 = result.transaction.asInstanceOf[Transaction1559]

    assert(transaction1559.chainId == resultTransaction1559.chainId)
    assert(transaction1559.nonce == resultTransaction1559.nonce)
    assert(transaction1559.maxFeePerGas == resultTransaction1559.maxFeePerGas)
    assert(transaction1559.maxPriorityFeePerGas == resultTransaction1559.maxPriorityFeePerGas)
    assert(transaction1559.gasLimit == resultTransaction1559.gasLimit)
    assert(transaction1559.to == resultTransaction1559.to)
    assert(transaction1559.value == resultTransaction1559.value)
    assert(transaction1559.data == resultTransaction1559.data)
  }
//

end TransactionDecoderTest
