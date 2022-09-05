package org.web3s.protocol.besu

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.Web3sBesu
import org.web3s.protocol.core.Web3sServiceResponseJsonTest
import org.web3s.protocol.besu.methods.response.*
import org.web3s.protocol.besu.methods.response.privacy.*
import org.web3s.protocol.besu.methods.response.model.*
import org.web3s.protocol.core.methods.response.*
import org.web3s.protocol.core.{DefaultBlockParameter, Web3sServiceRequestJsonTest}
import org.web3s.protocol.core.DefaultBlockParameterName
import org.web3s.utils.Numeric
import org.web3s.utils.EthBigInt
import org.web3s.protocol.eea.util.*
import org.web3s.protocol.eea.util.codecs.given
import scala.util.Try


class ResponseTest extends AnyFunSuite :

  private val web3sServiceResponse = new Web3sServiceResponseJsonTest

  val web3sBesu: Besu[Try] = new Web3sBesu(web3sServiceResponse)

  test("ClicqueGetSigners") {
    web3sServiceResponse.buildResponse("{\n"
      + "    \"jsonrpc\": \"2.0\",\n"
      + "    \"id\": 1,\n"
      + "    \"result\": [\"0x42eb768f2244c8811c63729a21a3569731535f06\","
      + "\"0x7ffc57839b00206d1ad20c69a1981b489f772031\","
      + "\"0xb279182d99e65703f0076e4812653aab85fca0f0\"]\n"
      + "}")
    val response = web3sBesu.cliqueGetSigners(DefaultBlockParameterName.LATEST)
    assert(response.get.accounts == Seq("0x42eb768f2244c8811c63729a21a3569731535f06", "0x7ffc57839b00206d1ad20c69a1981b489f772031", "0xb279182d99e65703f0076e4812653aab85fca0f0"))
  }


  test("ClicqueProposals") {
    web3sServiceResponse.buildResponse("{\n"
      + "    \"jsonrpc\": \"2.0\",\n"
      + "    \"id\": 1,\n"
      + "    \"result\": {\"0x42eb768f2244c8811c63729a21a3569731535f07\": false,"
      + "\"0x12eb759f2222d7711c63729a45c3585731521d01\": true}\n}")
    val response = web3sBesu.cliqueProposals
    assert(response.get.accountMaps == Map("0x42eb768f2244c8811c63729a21a3569731535f07" -> false, "0x12eb759f2222d7711c63729a45c3585731521d01" -> true))
  }

  test("IbftGetValidatorMetrics") {
    web3sServiceResponse.buildResponse("{\n"
      + "    \"jsonrpc\": \"2.0\",\n"
      + "    \"id\": 1,\n"
      + "    \"result\": [\n"
      + "{\"address\": \"0x42eb768f2244c8811c63729a21a3569731535f07\",\n"
      + "\"proposedBlockCount\": \"0x0\",\n"
      + "\"lastProposedBlockNumber\": \"0x1\"}\n"
      + "]\n"
      + "}")
    val response = web3sBesu.ibftGetSignerMetrics
    assert(response.get.signerMetrics.head.address == "0x42eb768f2244c8811c63729a21a3569731535f07")
    assert(response.get.signerMetrics.head.proposedBlockCount.value == BigInt(0))
    assert(response.get.signerMetrics.head.lastProposedBlockNumber.value == BigInt(1))
  }

  test("PrivGetPrivateTransactionLegacy") {
    import cats.kernel.Eq
    import cats.implicits._
    import cats.derived.semiauto
    given Eq[PrivateTransaction] = semiauto.eq

    web3sServiceResponse.buildResponse("{\n"
      + "    \"id\":1,\n"
      + "    \"jsonrpc\":\"2.0\",\n"
      + "    \"result\": {\n"
      + "        \"hash\":\"0xc6ef2fc5426d6ad6fd9e2a26abeab0aa2411b7ab17f30a99d3cb96aed1d1055b\",\n"
      + "        \"nonce\":\"0x0\",\n"
      + "        \"from\":\"0x407d73d8a49eeb85d32cf465507dd71d507100c1\",\n"
      + "        \"to\":\"0x85h43d8a49eeb85d32cf465507dd71d507100c1\",\n"
      + "        \"value\":\"0x7f110\",\n"
      + "        \"gas\": \"0x7f110\",\n"
      + "        \"gasPrice\":\"0x9184e72a000\",\n"
      + "        \"input\":\"0x603880600c6000396000f300603880600c6000396000f3603880600c6000396000f360\",\n"
      + "        \"r\":\"0xf115cc4d7516dd430046504e1c888198e0323e8ded016d755f89c226ba3481dc\",\n"
      + "        \"s\":\"0x4a2ae8ee49f1100b5c0202b37ed8bacf4caeddebde6b7f77e12e7a55893e9f62\",\n"
      + "        \"v\":\"0x0\",\n"
      + "        \"privateFrom\":\"A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo=\",\n"
      + "        \"privateFor\":[\"A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo=\",\"Ko2bVqD+nNlNYL5EE7y3IdOnviftjiizpjRt+HTuFBs=\"],\n"
      + "        \"restriction\":\"restricted\""
      + "  }\n"
      + "}")
    val response = web3sBesu.privGetPrivateTransaction("")
    val transaction = PrivateTransaction(
      hash = "0xc6ef2fc5426d6ad6fd9e2a26abeab0aa2411b7ab17f30a99d3cb96aed1d1055b",
      nonce = "0x0",
      from = "0x407d73d8a49eeb85d32cf465507dd71d507100c1",
      to = "0x85h43d8a49eeb85d32cf465507dd71d507100c1",
      value = "0x7f110",
      gas = "0x7f110",
      gasPrice = "0x9184e72a000",
      input = "0x603880600c6000396000f300603880600c6000396000f3603880600c6000396000f360",
      r = "0xf115cc4d7516dd430046504e1c888198e0323e8ded016d755f89c226ba3481dc",
      s = "0x4a2ae8ee49f1100b5c0202b37ed8bacf4caeddebde6b7f77e12e7a55893e9f62",
      v = "0x0",
      privateFrom = Base64String.wrap("A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo="),
      privateFor = Base64String.wrapList(
        List("A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo=", "Ko2bVqD+nNlNYL5EE7y3IdOnviftjiizpjRt+HTuFBs=")),
      restriction = Restriction.RESTRICTED)

    assert(response.get.privateTransaction.get === transaction)
  }


  test("PrivGetPrivateTransactionPrivacyGroup") {
    import cats.kernel.Eq
    import cats.implicits._
    import cats.derived.semiauto
    given Eq[PrivateTransaction] = semiauto.eq


    web3sServiceResponse.buildResponse("{\n"
      + "    \"id\":1,\n"
      + "    \"jsonrpc\":\"2.0\",\n"
      + "    \"result\": {\n"
      + "        \"hash\":\"0xc6ef2fc5426d6ad6fd9e2a26abeab0aa2411b7ab17f30a99d3cb96aed1d1055b\",\n"
      + "        \"nonce\":\"0x0\",\n"
      + "        \"from\":\"0x407d73d8a49eeb85d32cf465507dd71d507100c1\",\n"
      + "        \"to\":\"0x85h43d8a49eeb85d32cf465507dd71d507100c1\",\n"
      + "        \"value\":\"0x7f110\",\n"
      + "        \"gas\": \"0x7f110\",\n"
      + "        \"gasPrice\":\"0x9184e72a000\",\n"
      + "        \"input\":\"0x603880600c6000396000f300603880600c6000396000f3603880600c6000396000f360\",\n"
      + "        \"r\":\"0xf115cc4d7516dd430046504e1c888198e0323e8ded016d755f89c226ba3481dc\",\n"
      + "        \"s\":\"0x4a2ae8ee49f1100b5c0202b37ed8bacf4caeddebde6b7f77e12e7a55893e9f62\",\n"
      + "        \"v\":\"0x0\",\n"
      + "        \"privateFrom\":\"A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo=\",\n"
      + "        \"privateFor\":\"A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo=\",\n"
      + "        \"restriction\":\"restricted\""
      + "  }\n"
      + "}")
    val response = web3sBesu.privGetPrivateTransaction("")
    val transaction = PrivateTransaction(
      "0xc6ef2fc5426d6ad6fd9e2a26abeab0aa2411b7ab17f30a99d3cb96aed1d1055b",
      "0x0",
      "0x407d73d8a49eeb85d32cf465507dd71d507100c1",
      "0x85h43d8a49eeb85d32cf465507dd71d507100c1",
      "0x7f110",
      "0x7f110",
      "0x9184e72a000",
      "0x603880600c6000396000f300603880600c6000396000f3603880600c6000396000f360",
      "0xf115cc4d7516dd430046504e1c888198e0323e8ded016d755f89c226ba3481dc",
      "0x4a2ae8ee49f1100b5c0202b37ed8bacf4caeddebde6b7f77e12e7a55893e9f62",
      "0x0",
      Base64String.wrap("A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo="),
      Base64String.wrap("A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo="),
      restriction = Restriction.RESTRICTED)

    assert(response.get.privateTransaction.get === transaction)
  }

  test("PrivGetPrivateTransactionNull") {

    web3sServiceResponse.buildResponse("{\n"
      + "    \"jsonrpc\": \"2.0\",\n"
      + "    \"id\": 1,\n"
      + "    \"result\": null\n"
      + "}")
    val response = web3sBesu.privGetPrivateTransaction("")

    assert(response.get.privateTransaction.isEmpty)
  }


  test("PrivDistributeRawTransaction") {

    web3sServiceResponse.buildResponse("{\n"
      + "    \"jsonrpc\": \"2.0\",\n"
      + "    \"id\": 1,\n"
      + "    \"result\": \"0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331\"\n"
      + "}")
    val response = web3sBesu.privDistributeRawTransaction("")

    assert(response.get.key == "0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331")
  }

  test("PrivGetPrivacyPrecompileAddress") {

    web3sServiceResponse.buildResponse("{\n"
      + "    \"jsonrpc\": \"2.0\",\n"
      + "    \"id\": 1,\n"
      + "    \"result\": \"0xb60e8dd61c5d32be8058bb8eb970870f07233155\"\n"
      + "}")
    val response = web3sBesu.privGetPrivacyPrecompileAddress

    assert(response.get.precompileAddress == "0xb60e8dd61c5d32be8058bb8eb970870f07233155")
  }


  test("PrivCreatePrivacyGroup") {

    web3sServiceResponse.buildResponse("{\n"
      + "    \"jsonrpc\": \"2.0\",\n"
      + "    \"id\": 1,\n"
      + "    \"result\": \"DyAOiF/ynpc+JXa2YAGB0bCitSlOMNm+ShmB/7M6C4w=\"\n"
      + "}")
    val response = web3sBesu.privCreatePrivacyGroup(Nil, "", "")

    assert(response.get.privacyGroupId.asString == "DyAOiF/ynpc+JXa2YAGB0bCitSlOMNm+ShmB/7M6C4w=")
  }

  test("PrivDeletePrivacyGroup") {

    web3sServiceResponse.buildResponse("{\n"
      + "    \"jsonrpc\": \"2.0\",\n"
      + "    \"id\": 1,\n"
      + "    \"result\": true\n"
      + "}")
    val response = web3sBesu.privDeletePrivacyGroup(Base64String.wrap("DyAOiF/ynpc+JXa2YAGB0bCitSlOMNm+ShmB/7M6C4w="))

    assert(response.get.isSuccess)
  }

  test("PrivFindPrivacyGroup") {
    import cats.kernel.Eq
    import cats.implicits._
    import cats.derived.semiauto
    given Eq[PrivacyGroup] = semiauto.eq

    web3sServiceResponse.buildResponse("{\n"
      + "    \"jsonrpc\": \"2.0\",\n"
      + "    \"id\": 1,\n"
      + "    \"result\": [\n"
      + "         {\n"
      + "            \"privacyGroupId\":\"A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo=\",\n"
      + "            \"name\":\"PrivacyGroupName\",\n"
      + "            \"description\":\"PrivacyGroupDescription\",\n"
      + "            \"type\":\"LEGACY\",\n"
      + "            \"members\": [\"A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo=\"]\n"
      + "         },\n"
      + "         {\n"
      + "            \"privacyGroupId\":\"A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo=\",\n"
      + "            \"name\":\"PrivacyGroupName\",\n"
      + "            \"description\":\"PrivacyGroupDescription\",\n"
      + "            \"type\":\"PANTHEON\",\n"
      + "            \"members\": [\"A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo=\"]\n"
      + "         },\n"
      + "         {\n"
      + "            \"privacyGroupId\":\"A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo=\",\n"
      + "            \"type\":\"ONCHAIN\",\n"
      + "            \"members\": [\"A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo=\"]\n"
      + "         }\n"
      + "    ]\n"
      + "}")
    val response = web3sBesu.privFindPrivacyGroup(Nil)
    val groups = List(
      PrivacyGroup(
        "A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo=",
        PrivacyGroupType.LEGACY,
        Some("PrivacyGroupName"),
          Some("PrivacyGroupDescription"),
        Base64String.wrapList("A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo=")),
        PrivacyGroup(
          "A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo=",
          PrivacyGroupType.PANTHEON,
          Some("PrivacyGroupName"),
          Some("PrivacyGroupDescription"),
          Base64String.wrapList("A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo=")),
        PrivacyGroup(
          "A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo=",
          PrivacyGroupType.ONCHAIN,
          None,
          None,
          Base64String.wrapList("A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo="))
      )
    assert(response.get.groups === groups )
  }

  test("PrivGetTransactionReceipt") {
    import cats.kernel.Eq
    import cats.implicits._
    import cats.derived.auto.eq

    web3sServiceResponse.buildResponse("{\n"
      + "    \"id\":1,\n"
      + "    \"jsonrpc\":\"2.0\",\n"
      + "    \"result\": {\n"
      + "        \"contractAddress\": \"0xb60e8dd61c5d32be8058bb8eb970870f07233155\",\n"
      + "        \"from\":\"0x407d73d8a49eeb85d32cf465507dd71d507100c1\",\n"
      + "        \"to\":\"0x85h43d8a49eeb85d32cf465507dd71d507100c1\",\n"
      + "        \"output\":\"myRlpEncodedOutputFromPrivateContract\",\n"
      + "        \"status\":\"0x1\",\n"
      + "        \"privacyGroupId\":\"Qlv2Jhn3C3/2KrPU7Jeu/9F6rElio9LNBSieb0Xk/Ro=\",\n"
      + "        \"commitmentHash\": \"0x75aaac4be865057a576872587c9672197f1bab25e64b588c81f483c5869e0fa7\",\n"
      + "        \"transactionHash\": \"0x5504d87dc6c6ab8ea4f5c988bcf1c41d40e6b594b80849d4444c432099ee6c34\",\n"
      + "        \"privateFrom\": \"A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo=\",\n"
      + "        \"logs\": [{\n"
      + "            \"removed\": false,\n"
      + "            \"logIndex\": \"0x1\",\n"
      + "            \"transactionIndex\": \"0x0\",\n"
      + "            \"transactionHash\": \"0xdf829c5a142f1fccd7d8216c5785ac562ff41e2dcfdf5785ac562ff41e2dcf\",\n"
      + "            \"blockHash\": \"0x8216c5785ac562ff41e2dcfdf5785ac562ff41e2dcfdf829c5a142f1fccd7d\",\n"
      + "            \"blockNumber\":\"0x1b4\",\n"
      + "            \"address\": \"0x16c5785ac562ff41e2dcfdf829c5a142f1fccd7d\",\n"
      + "            \"data\":\"0x0000000000000000000000000000000000000000000000000000000000000000\",\n"
      + "            \"type\":\"mined\",\n"
      + "            \"topics\": [\"0x59ebeb90bc63057b6515673c3ecf9438e5058bca0f92585014eced636878c9a5\"]"
      + "        }]\n"
      + "    }\n"
      + "}")
    val response = web3sBesu.privGetTransactionReceipt("")
    val transactionReceipt = PrivateTransactionReceipt(
      "0xb60e8dd61c5d32be8058bb8eb970870f07233155",
      "0x407d73d8a49eeb85d32cf465507dd71d507100c1",
      "0x85h43d8a49eeb85d32cf465507dd71d507100c1",
      "myRlpEncodedOutputFromPrivateContract",
      List(
        EthLog.Log(
          false,
          "0x1",
          "0x0",
          "0xdf829c5a142f1fccd7d8216c5785ac562ff41e2dcfdf5785ac562ff41e2dcf",
          "0x8216c5785ac562ff41e2dcfdf5785ac562ff41e2dcfdf829c5a142f1fccd7d",
          "0x1b4",
          "0x16c5785ac562ff41e2dcfdf829c5a142f1fccd7d",
          "0x0000000000000000000000000000000000000000000000000000000000000000",
          "mined",
          List(
            "0x59ebeb90bc63057b6515673c3ecf9438e5058bca0f92585014eced636878c9a5"))),
      "0x75aaac4be865057a576872587c9672197f1bab25e64b588c81f483c5869e0fa7",
      "0x5504d87dc6c6ab8ea4f5c988bcf1c41d40e6b594b80849d4444c432099ee6c34",
      "A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo=",
      None,
      "Qlv2Jhn3C3/2KrPU7Jeu/9F6rElio9LNBSieb0Xk/Ro=",
      "0x1",
      None)

    assert(response.get.privateTransactionReceipt === transactionReceipt)
  }

  test("FullDebugTraceInfo") {

    web3sServiceResponse.buildResponse("{\n"
      + "\"jsonrpc\": \"2.0\",\n"
      + "\"id\": 1,\n"
      + "\"result\": {\n"
      + "  \"gas\":35956,"
      + "  \"returnValue\":\"1\","
      + "  \"structLogs\":[\n"
      + "      {\"depth\":0,\"error\":\"\",\"gas\":1478712,\"gasCost\":3,\"memory\":[],\"op\":\"PUSH1\",\"pc\":0,\"stack\":[],\"storage\":{}},"
      + "      {\"depth\":0,\"error\":\"\",\"gas\":1478709,\"gasCost\":3,\"memory\":[],\"op\":\"PUSH1\",\"pc\":2,\"stack\":[\"0000000000000000000000000000000000000000000000000000000000000080\"],\"storage\":{}},"
      + "      {\"depth\":0,\"error\":\"\",\"gas\":1477248,\"gasCost\":3,\"memory\":[\"0000000000000000000000000000000000000000000000000000000000000000\",\"0000000000000000000000000000000000000000000000000000000000000000\",\"0000000000000000000000000000000000000000000000000000000000000080\"],\"op\":\"DUP3\",\"pc\":6173,\"stack\":[\"00000000000000000000000000000000000000000000000000000000a0712d68\",\"0000000000000000000000000000000000000000000000000000000000000279\",\"00000000000000000000000000000000000000000000016929fc579f2cf60000\",\"00000000000000000000000000000000000000000000000000000000000006e5\",\"000000000000000000000000bed92733f5549af6411355d5fe12781744248f96\",\"00000000000000000000000000000000000000000000016929fc579f2cf60000\",\"00000000000000000000000000000000000000000000016929fc579f2cf60000\",\"0000000000000000000000000000000000000000000000000000000000000002\",\"0000000000000000000000000000000000000000000000000000000000000000\",\"0000000000000000000000000000000000000000000000000000000000000f31\",\"00000000000000000000000000000000000000000000016929fc579f2cf60000\",\"000000000000000000000000000000000000000000544a2efc54e6eb8bd90400\",\"0000000000000000000000000000000000000000000000000000000000000000\",\"fffffffffffffffffffffffffffffffffffffffffffffe96d603a860d309ffff\"],\"storage\":{\"0000000000000000000000000000000000000000000000000000000000000002\":\"000000000000000000000000000000000000000000544a2efc54e6eb8bd90400\"}}"
      + "  ]"
      + "}\n"
      + "}")
    val response = web3sBesu.debugTraceTransaction("",Map.empty)

    assert(response.get.fullDebugTraceInfo.failed.isEmpty )
    assert(response.get.fullDebugTraceInfo.gas == 35956 )
    assert(response.get.fullDebugTraceInfo.returnValue == "1" )
    assert(response.get.fullDebugTraceInfo.structLogs.head.pc == 0)
    assert(response.get.fullDebugTraceInfo.structLogs(2).storage.get(BigInt("0000000000000000000000000000000000000000000000000000000000000002").toLong).contains("000000000000000000000000000000000000000000544a2efc54e6eb8bd90400"))
  }