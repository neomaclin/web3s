package org.web3s.protocol.besu

import org.web3s.protocol.eea.util.*
import org.scalatest.funsuite.AnyFunSuite
import org.web3s.Web3sBesu
import org.web3s.protocol.core.Web3sServiceRequestJsonTest
import org.web3s.protocol.core.methods.request.{EthFilter, FilterTopic, SingleTopic, Transaction}
import org.web3s.protocol.core.{DefaultBlockParameter, DefaultBlockParameterName, Web3sServiceRequestJsonTest}

import scala.util.Try

class RequestTest extends AnyFunSuite :

  private val MOCK_ENCLAVE_KEY = Base64String.wrap("A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo=")
  private val MOCK_ENCLAVE_KEY_2 = Base64String.wrap("Ko2bVqD+nNlNYL5EE7y3IdOnviftjiizpjRt+HTuFBs=")
  private val MOCK_PRIVACY_GROUP_ID = Base64String.wrap("DyAOiF/ynpc+JXa2YAGB0bCitSlOMNm+ShmB/7M6C4w=")
  private val BASE_64_STRINGS: List[Base64String] = List(MOCK_ENCLAVE_KEY, MOCK_ENCLAVE_KEY_2)

  private val web3sServiceRequestJsonTest = new Web3sServiceRequestJsonTest
  val web3sBesu: Besu[Try] = new Web3sBesu(web3sServiceRequestJsonTest)

  test("MinerStart") {
    web3sBesu.minerStart
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"miner_start","params":[],"id":1}""")
  }

  test("MinerStop") {
    web3sBesu.minerStop
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"miner_stop","params":[],"id":1}""")
  }

  test("ClicqueDiscard") {
    web3sBesu.cliqueDiscard("0xFE3B557E8Fb62b89F4916B721be55cEb828dBd73")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"clique_discard","params":["0xFE3B557E8Fb62b89F4916B721be55cEb828dBd73"],"id":1}""")
  }

  test("ClicqueGetSigners") {
    val blockParameter = DefaultBlockParameter.valueOf("latest")

    web3sBesu.cliqueGetSigners(blockParameter)
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"clique_getSigners","params":["latest"],"id":1}""")
  }

  test("ClicqueGetSignersAtHash") {
    val blockHash = "0x98b2ddb5106b03649d2d337d42154702796438b3c74fd25a5782940e84237a48"
    web3sBesu.cliqueGetSignersAtHash(blockHash)
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"clique_getSignersAtHash","params":["0x98b2ddb5106b03649d2d337d42154702796438b3c74fd25a5782940e84237a48"],"id":1}""")
  }

  test("ClicquePropose") {
    val signerAddress = "0xFE3B557E8Fb62b89F4916B721be55cEb828dBd73"
    web3sBesu.cliquePropose(signerAddress, true)
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"clique_propose","params":["0xFE3B557E8Fb62b89F4916B721be55cEb828dBd73",true],"id":1}""")
  }

  test("ClicqueProposals") {
    web3sBesu.cliqueProposals
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"clique_proposals","params":[],"id":1}""")
  }

  test("DebugTraceTransaction") {
    val transactionHash = "0xc171033d5cbff7175f29dfd3a63dda3d6f8f385e"

    val options = Map(
      "disableStorage" -> false,
      "disableStack" -> false,
      "disableMemory" -> true
    )
    web3sBesu.debugTraceTransaction(transactionHash, options)
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"debug_traceTransaction","params":["0xc171033d5cbff7175f29dfd3a63dda3d6f8f385e",{"disableMemory":true,"disableStorage":false,"disableStack":false}],"id":1}""")
  }

  test("ibftDiscardValidatorVote") {
    val accountId = "0xFE3B557E8Fb62b89F4916B721be55cEb828dBd73"
    web3sBesu.ibftDiscardValidatorVote(accountId)
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"ibft_discardValidatorVote","params":["0xFE3B557E8Fb62b89F4916B721be55cEb828dBd73"],"id":1}""")
  }

  test("ibftGetPendingVotes") {
    web3sBesu.ibftGetPendingVotes
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"ibft_getPendingVotes","params":[],"id":1}""")
  }

  test("ibftGetValidatorsByBlockNumber") {
    val blockParameter = DefaultBlockParameter.valueOf("latest")

    web3sBesu.ibftGetValidatorsByBlockNumber(blockParameter)
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"ibft_getValidatorsByBlockNumber","params":["latest"],"id":1}""")
  }


  test("ibftGetValidatorsByBlockHash") {
    val blockHash =
      "0x98b2ddb5106b03649d2d337d42154702796438b3c74fd25a5782940e84237a48"

    web3sBesu.ibftGetValidatorsByBlockHash(blockHash)
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"ibft_getValidatorsByBlockHash","params":["0x98b2ddb5106b03649d2d337d42154702796438b3c74fd25a5782940e84237a48"],"id":1}""")
  }

  test("ibftProposeValidatorVote") {
    val validatorAddress = "0xFE3B557E8Fb62b89F4916B721be55cEb828dBd73"
    web3sBesu.ibftProposeValidatorVote(validatorAddress, true)
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"ibft_proposeValidatorVote","params":["0xFE3B557E8Fb62b89F4916B721be55cEb828dBd73",true],"id":1}""")
  }

  test("PrivGetTransactionCount") {

    web3sBesu.privGetTransactionCount("0x407d73d8a49eeb85d32cf465507dd71d507100c1", MOCK_PRIVACY_GROUP_ID)
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"priv_getTransactionCount","params":["0x407d73d8a49eeb85d32cf465507dd71d507100c1","DyAOiF/ynpc+JXa2YAGB0bCitSlOMNm+ShmB/7M6C4w="],"id":1}""")
  }

  test("PrivDistributeRawTransaction") {

    web3sBesu.privDistributeRawTransaction("0xd46e8dd67c5d32be8d46e8dd67c5d32be8058bb8eb970870f072445675058bb8eb970870f072445675")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"priv_distributeRawTransaction","params":["0xd46e8dd67c5d32be8d46e8dd67c5d32be8058bb8eb970870f072445675058bb8eb970870f072445675"],"id":1}""")
  }

  test("PrivGetPrivateTransaction") {

    web3sBesu.privGetPrivateTransaction("EnclaveKey")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"priv_getPrivateTransaction","params":["EnclaveKey"],"id":1}""")
  }

  test("PrivGetPrivacyPrecompileAddress") {

    web3sBesu.privGetPrivacyPrecompileAddress
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"priv_getPrivacyPrecompileAddress","params":[],"id":1}""")
  }

  test("PrivCreatePrivacyGroup") {

    web3sBesu.privCreatePrivacyGroup(BASE_64_STRINGS, "testName", "testDesc")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"priv_createPrivacyGroup","params":[{"addresses":["A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo=","Ko2bVqD+nNlNYL5EE7y3IdOnviftjiizpjRt+HTuFBs="],"name":"testName","description":"testDesc"}],"id":1}""")
  }

  test("PrivFindPrivacyGroup") {

    web3sBesu.privFindPrivacyGroup(BASE_64_STRINGS)
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"priv_findPrivacyGroup","params":[["A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo=","Ko2bVqD+nNlNYL5EE7y3IdOnviftjiizpjRt+HTuFBs="]],"id":1}""")
  }

  test("PrivDeletePrivacyGroup") {

    web3sBesu.privDeletePrivacyGroup(MOCK_PRIVACY_GROUP_ID)
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"priv_deletePrivacyGroup","params":["DyAOiF/ynpc+JXa2YAGB0bCitSlOMNm+ShmB/7M6C4w="],"id":1}""")
  }

  test("PrivGetTransactionReceipt") {

    web3sBesu.privGetTransactionReceipt("0x123")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"priv_getTransactionReceipt","params":["0x123"],"id":1}""")
  }

  test("PrivGetCode") {

    web3sBesu.privGetCode(
      MOCK_PRIVACY_GROUP_ID.asString,
      "A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo=",
      DefaultBlockParameterName.LATEST)
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"priv_getCode","params":["DyAOiF/ynpc+JXa2YAGB0bCitSlOMNm+ShmB/7M6C4w=","A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo=","latest"],"id":1}""")
  }

  test("PrivCall") {

    web3sBesu.privCall(
      MOCK_PRIVACY_GROUP_ID.asString,
      Transaction.createEthCallTransaction(
        "0xa70e8dd61c5d32be8058bb8eb970870f07233155",
        "0xb60e8dd61c5d32be8058bb8eb970870f07233155",
        "0x0"),
      DefaultBlockParameter.valueOf("latest"))
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"priv_call","params":["DyAOiF/ynpc+JXa2YAGB0bCitSlOMNm+ShmB/7M6C4w=",{"from":"0xa70e8dd61c5d32be8058bb8eb970870f07233155","to":"0xb60e8dd61c5d32be8058bb8eb970870f07233155","data":"0x0"},"latest"],"id":1}""")
  }

  test("PrivGetLogs") {
    val ethFilter = EthFilter().copy(topics = List[FilterTopic[_]](SingleTopic("0x000000000000000000000000a94f5374fce5edbc8e2a8697c15331677e6ebf0b")))
    web3sBesu.privGetLogs(MOCK_PRIVACY_GROUP_ID.asString, ethFilter)
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"priv_getLogs","params":["DyAOiF/ynpc+JXa2YAGB0bCitSlOMNm+ShmB/7M6C4w=",{"topics":["0x000000000000000000000000a94f5374fce5edbc8e2a8697c15331677e6ebf0b"]}],"id":1}""")
  }

  test("PrivNewFilter") {
    val ethFilter = EthFilter().copy(topics = List[FilterTopic[_]](SingleTopic("0x12341234")))

    web3sBesu.privNewFilter(MOCK_PRIVACY_GROUP_ID.asString, ethFilter)
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"priv_newFilter","params":["DyAOiF/ynpc+JXa2YAGB0bCitSlOMNm+ShmB/7M6C4w=",{"topics":["0x12341234"]}],"id":1}""")
  }

  test("PrivUninstallFilter") {

    web3sBesu.privUninstallFilter(
      MOCK_PRIVACY_GROUP_ID.asString, "0x13e9b67497fa859338ecba166752591b")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"priv_uninstallFilter","params":["DyAOiF/ynpc+JXa2YAGB0bCitSlOMNm+ShmB/7M6C4w=","0x13e9b67497fa859338ecba166752591b"],"id":1}""")
  }

  test("PrivGetFilterChanges") {

    web3sBesu.privGetFilterChanges(
      MOCK_PRIVACY_GROUP_ID.asString, "0x13e9b67497fa859338ecba166752591b")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"priv_getFilterChanges","params":["DyAOiF/ynpc+JXa2YAGB0bCitSlOMNm+ShmB/7M6C4w=","0x13e9b67497fa859338ecba166752591b"],"id":1}""")
  }

  test("PrivGetFilterLogs") {

    web3sBesu.privGetFilterLogs(
      MOCK_PRIVACY_GROUP_ID.asString, "0x13e9b67497fa859338ecba166752591b")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"priv_getFilterLogs","params":["DyAOiF/ynpc+JXa2YAGB0bCitSlOMNm+ShmB/7M6C4w=","0x13e9b67497fa859338ecba166752591b"],"id":1}""")
  }