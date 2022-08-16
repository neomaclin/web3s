package org.web3s.protocol.eea

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.protocol.core.Web3sServiceRequestJsonTest
import org.web3s.Web3sEea
import scala.util.Try

class RequestTest extends AnyFunSuite :

  val web3sServiceRequestJsonTest = new Web3sServiceRequestJsonTest
  val web3sEea: Eea[Try] = new Web3sEea(web3sServiceRequestJsonTest)

  test("EthSendRawTransaction") {
    web3sEea.eeaSendRawTransaction("0xd46e8dd67c5d32be8d46e8dd67c5d32be8058bb8eb970870f072445675058bb8eb970870f072445675")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eea_sendRawTransaction","params":["0xd46e8dd67c5d32be8d46e8dd67c5d32be8058bb8eb970870f072445675058bb8eb970870f072445675"],"id":1}""")
  }