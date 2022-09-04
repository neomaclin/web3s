package org.web3s.protocol.eea

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.Web3sEea
import org.web3s.protocol.core.Web3sServiceResponseJsonTest
import org.web3s.protocol.core.methods.response.*
import org.web3s.utils.Numeric

import scala.util.Try

class ResponseTest extends AnyFunSuite :


  private val web3sServiceResponse = new Web3sServiceResponseJsonTest

  val web3sEea: Eea[Try] = new Web3sEea(web3sServiceResponse)

  test("EeaSendRawTransaction") {
    web3sServiceResponse.buildResponse("{\n"
      + "    \"jsonrpc\": \"2.0\",\n"
      + "    \"id\": 1,\n"
      + "    \"result\": \"0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331\"\n"
      + "}")
    val response = web3sEea.eeaSendRawTransaction("")
    assert(response.get.transactionHash == "0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331")
  }
