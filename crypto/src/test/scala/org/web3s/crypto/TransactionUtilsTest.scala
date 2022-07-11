package org.web3s.crypto

import org.web3s.crypto.transaction.Transaction
import org.scalatest.funsuite.AnyFunSuite


class TransactionUtilsTest extends AnyFunSuite :
//  test("GenerateTransactionHash") {
//    assertEquals(generateTransactionHashHexEncoded(TransactionEncoderTest.createContractTransaction, SampleKeys.CREDENTIALS), "0xc3a0f520404c8cd0cb1c98be6b8e17ee32bf134ac1697d078e90422525c2d902")
////
//  @Test def testGenerateEip155TransactionHash() = assertEquals(generateTransactionHashHexEncoded(TransactionEncoderTest.createContractTransaction, 1.toByte, SampleKeys.CREDENTIALS), "0x568c7f6920c1cee8332e245c473657b9c53044eb96ed7532f5550f1139861e9e")

  test("deriveChainIdWhenMainNet") {
    val v = 37
    val chainId = Transaction.deriveChainId(v)
    assert(1 == chainId)
  }

  test("deriveChainIdWhenRopstenNet")  {
    val v = 42
    val chainId = Transaction.deriveChainId(v)
    assert(3 == chainId)
  }

  test("deriveChainIdWhenLegacySignature") {
    val v1 = 27
    val v2 = 28
    val chainId_1 = Transaction.deriveChainId(v1)
    val chainId_2 = Transaction.deriveChainId(v2)
    assert(0 == chainId_1)
    assert(0 == chainId_2)
  }

end TransactionUtilsTest
