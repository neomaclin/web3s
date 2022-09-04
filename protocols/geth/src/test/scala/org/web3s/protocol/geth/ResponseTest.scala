package org.web3s.protocol.geth

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.Web3Geth
import org.web3s.protocol.core.Web3sServiceResponseJsonTest
import org.web3s.protocol.geth.methods.response.*
import org.web3s.utils.Numeric

import scala.util.Try

class ResponseTest extends AnyFunSuite :


  private val web3sServiceResponse = new Web3sServiceResponseJsonTest

  val web3sGeth: Geth[Try] = new Web3Geth(web3sServiceResponse)

  test("PersonalEcRecover") {
    web3sServiceResponse.buildResponse("{\n"
      + "    \"jsonrpc\": \"2.0\",\n"
      + "    \"id\": 1,\n"
      + "    \"result\": \"0xadfc0262bbed8c1f4bd24a4a763ac616803a8c54\"\n"
      + "}")
    val response = web3sGeth.personalEcRecover("","")
    assert(response.get.recoverAccountId == "0xadfc0262bbed8c1f4bd24a4a763ac616803a8c54")
  }


  test("PersonalImportRawKey") {
    web3sServiceResponse.buildResponse(
      "{\n"
        + "    \"jsonrpc\": \"2.0\",\n"
        + "    \"id\": 1,\n"
        + "    \"result\": \"0xadfc0262bbed8c1f4bd24a4a763ac616803a8c54\"\n"
        + "}")
    val response = web3sGeth.personalImportRawKey("","")
    assert(response.get.accountId == "0xadfc0262bbed8c1f4bd24a4a763ac616803a8c54")
  }

