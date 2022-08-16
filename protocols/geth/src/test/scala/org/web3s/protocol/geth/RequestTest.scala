package org.web3s.protocol.geth

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.protocol.core.Web3sServiceRequestJsonTest
import org.web3s.Web3Geth
import scala.util.Try
class RequestTest extends AnyFunSuite :

  private val web3sServiceRequestJsonTest = new Web3sServiceRequestJsonTest
  val web3sGeth: Geth[Try] = new Web3Geth(web3sServiceRequestJsonTest)

  test("PersonalImportRawKey") {
    web3sGeth.personalImportRawKey("a08165236279178312660610114131826512483935470542850824183737259708197206310322", "hunter2")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"personal_importRawKey","params":["a08165236279178312660610114131826512483935470542850824183737259708197206310322","hunter2"],"id":1}""")
  }

  test("PersonalLockAccount") {
    val accountId = "0x407d73d8a49eeb85d32cf465507dd71d507100c1"
    web3sGeth.personalLockAccount(accountId)
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"personal_lockAccount","params":["0x407d73d8a49eeb85d32cf465507dd71d507100c1"],"id":1}""")
  }

  test("PersonalSign") {
    web3sGeth.personalSign("0xdeadbeaf", "0x9b2055d370f73ec7d8a03e965129118dc8f5bf83", "hunter2")

    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"personal_sign","params":["0xdeadbeaf","0x9b2055d370f73ec7d8a03e965129118dc8f5bf83","hunter2"],"id":1}""")
  }

  test("PersonalEcRecover") {
    web3sGeth.personalEcRecover("0xdeadbeaf", "0xa3f20717a250c2b0b729b7e5becbff67fdaef7e0699da4de7ca5895b02a170a12d887fd3b17bfdce3481f10bea41f45ba9f709d39ce8325427b57afcfc994cee1b")

    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"personal_ecRecover","params":["0xdeadbeaf","0xa3f20717a250c2b0b729b7e5becbff67fdaef7e0699da4de7ca5895b02a170a12d887fd3b17bfdce3481f10bea41f45ba9f709d39ce8325427b57afcfc994cee1b"],"id":1}""")
  }

  test("MinerStart") {
    web3sGeth.minerStart(4)
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"miner_start","params":[4],"id":1}""")
  }

  test("MinerStop") {
    web3sGeth.minerStop
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"miner_stop","params":[],"id":1}""")
  }