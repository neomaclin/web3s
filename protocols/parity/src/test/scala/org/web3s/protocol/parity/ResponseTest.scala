package org.web3s.protocol.parity

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.{Web3sParity, Web3sTrace}
import org.web3s.protocol.core.Web3sServiceResponseJsonTest
import org.web3s.protocol.parity.methods.response.*
import org.web3s.protocol.parity.methods.response.model.*
import org.web3s.protocol.parity.methods.response.decoders.given
import org.web3s.utils.Numeric
import org.web3s.crypto.Wallet
import org.web3s.protocol.parity.Trace as TraceF

import scala.util.Try

class ResponseTest extends AnyFunSuite :


  private val web3sServiceResponse = new Web3sServiceResponseJsonTest


  val parity: Parity[Try] = new Web3sParity(web3sServiceResponse)
  val trace: TraceF[Try] = new Web3sTrace(web3sServiceResponse)

  test("ParityAddressesResponse") {
    web3sServiceResponse.buildResponse("{\n"
      + "    \"jsonrpc\": \"2.0\",\n"
      + "    \"id\": 1,\n"
      + "    \"result\": [\"0x407d73d8a49eeb85d32cf465507dd71d507100c1\"]\n"
      + "}")
    val response = parity.parityGetNewDappsAddresses
    assert(response.get.addresses == List("0x407d73d8a49eeb85d32cf465507dd71d507100c1"))
  }


  test("ParityAllAccountsInfo") {
    web3sServiceResponse.buildResponse(
      "{\n"
        + "    \"jsonrpc\": \"2.0\",\n"
        + "    \"id\": 1,\n"
        + "    \"result\": {\n"
        + "        \"0x00a289b43e1e4825dbedf2a78ba60a640634dc40\": {\n"
        + "            \"meta\": {},\n"
        + "            \"name\": \"Savings\",\n"
        + "            \"uuid\": \"7fee0393-7571-2b4f-8672-862fea01a4a0\""
        + "            }\n"
        + "    }\n"
        + "}")
    val response = parity.parityAllAccountsInfo
    val accountsInfoMap = Map(
      "0x00a289b43e1e4825dbedf2a78ba60a640634dc40" -> ParityAllAccountsInfo.AccountsInfo(
        Map.empty, "Savings", "7fee0393-7571-2b4f-8672-862fea01a4a0"
      )
    )
    assert(response.get.accountsInfo == accountsInfoMap)
  }


  test("ParityAddressResponse") {
    web3sServiceResponse.buildResponse(
      "{\n"
        + "    \"jsonrpc\": \"2.0\",\n"
        + "    \"id\": 1,\n"
        + "    \"result\": \"0x407d73d8a49eeb85d32cf465507dd71d507100c1\"\n"
        + "}")
    val response = parity.parityGetNewDappsDefaultAddress

    assert(response.get.address == "0x407d73d8a49eeb85d32cf465507dd71d507100c1")
  }


  test("ParityExportAccount") {
    web3sServiceResponse.buildResponse(
      "{\n"
        + "    \"jsonrpc\": \"2.0\",\n"
        + "    \"id\": 1,\n"
        + "    \"result\": {\n"
        + "        \"address\": \"0042e5d2a662eeaca8a7e828c174f98f35d8925b\",\n"
        + "        \"crypto\": {\n"
        + "            \"cipher\": \"aes-128-ctr\",\n"
        + "                \"cipherparams\": {\n"
        + "                    \"iv\": \"a1c6ff99070f8032ca1c4e8add006373\"\n"
        + "                },\n"
        + "            \"ciphertext\": \"df27e3db64aa18d984b6439443f73660643c2d119a6f0fa2fa9a6456fc802d75\",\n"
        + "            \"kdf\": \"pbkdf2\",\n"
        + "            \"kdfparams\": {\n"
        + "                \"c\": 10240,\n"
        + "                \"dklen\": 32,\n"
        + "                \"prf\": \"hmac-sha256\",\n"
        + "                \"salt\": \"ddc325335cda5567a1719313e73b4842511f3e4a837c9658eeb78e51ebe8c815\"\n"
        + "            },\n"
        + "        \"mac\": \"3dc888ae79cbb226ff9c455669f6cf2d79be72120f2298f6cb0d444fddc0aa3d\"\n"
        + "        },\n"
        + "    \"id\": \"6a186c80-7797-cff2-bc2e-7c1d6a6cc76e\",\n"
        + "    \"meta\": \"{\\\"passwordHint\\\":\\\"parity-export-test\\\",\\\"timestamp\\\":1490017814987}\",\n"
        + "    \"name\": \"parity-export-test\",\n"
        + "    \"version\": 3\n"
        + "    }\n"
        + "}")
    val response = parity.parityExportAccount("", "")
    val walletFile = Wallet.WalletFile(
      address = Some("0042e5d2a662eeaca8a7e828c174f98f35d8925b"),
      crypto = Wallet.Crypto(
        cipher = "aes-128-ctr",
        ciphertext = "df27e3db64aa18d984b6439443f73660643c2d119a6f0fa2fa9a6456fc802d75",
        cipherparams = Wallet.CipherParams("a1c6ff99070f8032ca1c4e8add006373"),
        kdf = "pbkdf2",
        kdfparams =
          Wallet.Aes128CtrKdfParams(
            dklen = 32,
            c = 10240,
            prf = "hmac-sha256",
            salt = "ddc325335cda5567a1719313e73b4842511f3e4a837c9658eeb78e51ebe8c815"
          ),
        mac = "3dc888ae79cbb226ff9c455669f6cf2d79be72120f2298f6cb0d444fddc0aa3d"
      ),
      version = 3,
      id = "6a186c80-7797-cff2-bc2e-7c1d6a6cc76e"
    )
    assert(response.get.wallet == walletFile)
  }


  test("ParityListRecentDapps") {
    web3sServiceResponse.buildResponse(
      "{\n"
        + "    \"jsonrpc\": \"2.0\",\n"
        + "    \"id\": 1,\n"
        + "    \"result\": [\"web\"]\n"
        + "}")
    val response = parity.parityListRecentDapps
    assert(response.get.dappsIds == List("web"))
  }


  test("ParityFullTraceResponseStateDiff") {
    web3sServiceResponse.buildResponse(
      "{\n"
        + "    \"jsonrpc\": \"2.0\",\n"
        + "    \"id\":22,\n"
        + "    \"result\": {\n"
        + "        \"output\": \"0x\",\n"
        + "        \"stateDiff\": {\n"
        + "            \"0x00a0a24b9f0e5ec7aa4c7389b8302fd0123194de\": {\n"
        + "                \"balance\": {\n"
        + "                    \"*\": {\n"
        + "                        \"from\": \"0x2067ee238a4648bed5797\",\n"
        + "                        \"to\": \"0x2067ee23f5d09db3d0397\"\n"
        + "                    }\n"
        + "                },\n"
        + "                \"code\": \"=\",\n"
        + "                \"nonce\": \"=\",\n"
        + "                \"storage\": {}\n"
        + "            },\n"
        + "            \"0x14772e4f805b4dd2e69bd6d3f9b5edf0dfa5385a\": {\n"
        + "                \"balance\": {\n"
        + "                    \"*\": {\n"
        + "                        \"from\": \"0xf85a746b58c1fee\",\n"
        + "                        \"to\": \"0xf7eeea1663c73ee\"\n"
        + "                    }\n"
        + "                },\n"
        + "                \"code\": \"=\",\n"
        + "                \"nonce\": {\n"
        + "                    \"*\": {\n"
        + "                        \"from\": \"0x15\",\n"
        + "                        \"to\": \"0x16\"\n"
        + "                    }\n"
        + "                },\n"
        + "                \"storage\": {}\n"
        + "            },\n"
        + "            \"0x1a4298d0edc00618310e4c26f6479e5cccdfeaf8\": {\n"
        + "                \"balance\": {\n"
        + "                    \"+\": \"0x0\"\n"
        + "                },\n"
        + "                \"code\": {\n"
        + "                    \"+\": \"0x6060604052361561004a576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806341c0e1b51461004e578063b46300ec14610063575b5b5b005b341561005957600080fd5b61006161006d565b005b61006b6100ff565b005b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614156100fc576000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b5b565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc3073ffffffffffffffffffffffffffffffffffffffff16319081150290604051600060405180830381858888f19350505050151561017757600080fd5b5b5600a165627a7a72305820a6f301a38f55ea4c326a17bb26afad4aad7ed9dd49e1954d2b8995595e0ffceb0029\"\n"
        + "                },\n"
        + "                \"nonce\": {\n"
        + "                    \"+\": \"0x1\"\n"
        + "                },\n"
        + "                \"storage\": {\n"
        + "                    \"0x0000000000000000000000000000000000000000000000000000000000000000\": {\n"
        + "                        \"+\": \"0x00000000000000000000000014772e4f805b4dd2e69bd6d3f9b5edf0dfa5385a\"\n"
        + "                    }\n"
        + "                }\n"
        + "            }\n"
        + "        }"
        + "    },\n"
        + "    \"id\": 1\n"
        + "}")
    val response = trace.traceReplayTransaction("", Nil)

    val stateDiffMap = Map(
      "0x00a0a24b9f0e5ec7aa4c7389b8302fd0123194de" ->
        StateDiff(
          StateDiff.State.ChangedState(
            "0x2067ee238a4648bed5797", "0x2067ee23f5d09db3d0397"),
          StateDiff.State.UnchangedState,
          StateDiff.State.UnchangedState,
          Map.empty),

      "0x14772e4f805b4dd2e69bd6d3f9b5edf0dfa5385a" ->
        StateDiff(
          StateDiff.State.ChangedState("0xf85a746b58c1fee", "0xf7eeea1663c73ee"),
          StateDiff.State.UnchangedState,
          StateDiff.State.ChangedState("0x15", "0x16"),
          Map.empty),

      "0x1a4298d0edc00618310e4c26f6479e5cccdfeaf8" ->
        StateDiff(
          StateDiff.State.AddedState("0x0"),
          StateDiff.State.AddedState(
            "0x6060604052361561004a576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806341c0e1b51461004e578063b46300ec14610063575b5b5b005b341561005957600080fd5b61006161006d565b005b61006b6100ff565b005b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614156100fc576000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b5b565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc3073ffffffffffffffffffffffffffffffffffffffff16319081150290604051600060405180830381858888f19350505050151561017757600080fd5b5b5600a165627a7a72305820a6f301a38f55ea4c326a17bb26afad4aad7ed9dd49e1954d2b8995595e0ffceb0029"),
          StateDiff.State.AddedState("0x1"),
          Map("0x0000000000000000000000000000000000000000000000000000000000000000" ->
            StateDiff.State.AddedState(
              "0x00000000000000000000000014772e4f805b4dd2e69bd6d3f9b5edf0dfa5385a")))

    )
    val info = FullTraceInfo("0x", Some(stateDiffMap), None, None)
    assert(response.get.fullTraceInfo == info)
  }

  test("ParityFullTraceResponseTraces") {
    web3sServiceResponse.buildResponse(
      "{\n"
        + "    \"jsonrpc\": \"2.0\",\n"
        + "    \"id\":22,\n"
        + "    \"result\": {\n"
        + "        \"output\": \"0x\",\n"
        + "        \"stateDiff\": null,\n"
        + "        \"trace\": [{\n"
        + "                \"action\": {\n"
        + "                    \"from\": \"0x6c24f4387b31251fd7b6d7a1269d880b2108bf3a\",\n"
        + "                    \"gas\": \"0x4bc1f5\",\n"
        + "                    \"init\": \"0x606060405234156200000d57fe5b60405162004e4038038062004e40833981016040908152815\",\n"
        + "                    \"value\": \"0x0\"\n"
        + "                },\n"
        + "                \"blockHash\": \"0xf831abfdc67fb2ea3d16b6510b8e37d3d3e52ce3c25ed345053cb9503a430dd8\",\n"
        + "                \"blockNumber\": 4019912,\n"
        + "                \"result\": {\n"
        + "                    \"address\": \"0x1c9997559f6f1ae2ece8525eab68709f50865165\",\n"
        + "                    \"code\": \"0x606060405236156102b95763ffffffff60e060020a600035041663095ea7b381146102bb578063\",\n"
        + "                    \"gasUsed\": \"0x3ef6fe\"\n"
        + "                },\n"
        + "                \"subtraces\": 0,\n"
        + "                \"traceAddress\": [\n"
        + "\n"
        + "                ],\n"
        + "                \"transactionHash\": \"0x3d30d01a470dc4ae92e87f4c0068d640f880ce27e8b863780cbcb748f1654939\",\n"
        + "                \"transactionPosition\": 1,\n"
        + "                \"type\": \"create\"\n"
        + "            }, {\n"
        + "                \"action\": {\n"
        + "                    \"callType\": \"call\",\n"
        + "                    \"from\": \"0x14772e4f805b4dd2e69bd6d3f9b5edf0dfa5385a\",\n"
        + "                    \"gas\": \"0x4f6c5\",\n"
        + "                    \"input\": \"0xb46300ec\",\n"
        + "                    \"to\": \"0x781ab1a38837e351bfe1e318c6587766848abffa\",\n"
        + "                    \"value\": \"0x0\"\n"
        + "                },\n"
        + "                \"error\": \"Bad instruction\",\n"
        + "                \"subtraces\": 1,\n"
        + "                \"traceAddress\": [\n"
        + "\n"
        + "                ],\n"
        + "                \"type\": \"call\"\n"
        + "            }, {\n"
        + "                \"action\": {\n"
        + "                    \"address\": \"0xb8d2ac822f3d0445f5b83d32b0b176c2cb3d0e60\",\n"
        + "                    \"balance\": \"0x0\",\n"
        + "                    \"refundAddress\": \"0x14772e4f805b4dd2e69bd6d3f9b5edf0dfa5385a\"\n"
        + "                },\n"
        + "                \"blockHash\": \"0xf263b9364434a781057c467004e8d398d915529bdffa6f600d6d17fe733d3210\",\n"
        + "                \"blockNumber\": 3740614,\n"
        + "                \"result\": null,\n"
        + "                \"subtraces\": 0,\n"
        + "                \"traceAddress\": [\n"
        + "                    0\n"
        + "                ],\n"
        + "                \"transactionHash\": \"0xea6649db0f88d5400159853bf2c5b752ce724435dfb85b35f8725cf4cdc1ad6d\",\n"
        + "                \"transactionPosition\": 2,\n"
        + "                \"type\": \"suicide\"\n"
        + "            }, {\n"
        + "                \"action\": {\n"
        + "                    \"author\": \"0xb8d2ac822f3d0445f5b83d32b0b176c2cb3d0e60\",\n"
        + "                    \"value\": \"0x0\",\n"
        + "                    \"rewardType\": \"reward\"\n"
        + "                },\n"
        + "                \"blockHash\": \"0x8d9aff92ab07598b65ca3457dc68ee66de48ea0e1d4b0a658b6e39977b2a8542\",\n"
        + "                \"blockNumber\": 5676554,\n"
        + "                \"subtraces\": 0,\n"
        + "                \"traceAddress\": [\n"
        + "                    0\n"
        + "                ],\n"
        + "                \"type\": \"reward\"\n"
        + "            }\n"
        + "        ]\n"
        + "    },\n"
        + "    \"id\": 1\n"
        + "}")
    val response = trace.traceRawTransaction("", Nil)
    val traces = List(
      Trace(
        action = Trace.Action.CreateAction(from = "0x6c24f4387b31251fd7b6d7a1269d880b2108bf3a", gas = "0x4bc1f5", value = "0x0", init = "0x606060405234156200000d57fe5b60405162004e4038038062004e40833981016040908152815"),
        error = None,
        result = Some(Trace.Result("0x1c9997559f6f1ae2ece8525eab68709f50865165",
          "0x606060405236156102b95763ffffffff60e060020a600035041663095ea7b381146102bb578063",
          "0x3ef6fe",
          None)),
        subtraces = BigInt(0),
        traceAddress = Nil,
        `type` = "create",
        blockHash = Some("0xf831abfdc67fb2ea3d16b6510b8e37d3d3e52ce3c25ed345053cb9503a430dd8"),
        blockNumber = Some(BigInt(4019912)),
        transactionHash = Some("0x3d30d01a470dc4ae92e87f4c0068d640f880ce27e8b863780cbcb748f1654939"),
        transactionPosition = Some(BigInt(1))),
      Trace(
        action = Trace.Action.CallAction(callType = "call", from = "0x14772e4f805b4dd2e69bd6d3f9b5edf0dfa5385a", to = "0x781ab1a38837e351bfe1e318c6587766848abffa", gas = "0x4f6c5", input = "0xb46300ec", value = "0x0"),
        error = Some("Bad instruction"),
        result = None,
        subtraces = BigInt(1),
        traceAddress = Nil,
        `type` = "call",
        blockHash = None,
        blockNumber = None,
        transactionHash = None,
        transactionPosition = None),
      Trace(
        action = Trace.Action.SuicideAction(address = "0xb8d2ac822f3d0445f5b83d32b0b176c2cb3d0e60", balance = "0x0", refundAddress = "0x14772e4f805b4dd2e69bd6d3f9b5edf0dfa5385a"),
        error = None,
        result = None,
        subtraces = BigInt(0),
        traceAddress = List(BigInt(0)),
        `type` = "suicide",
        blockHash = Some("0xf263b9364434a781057c467004e8d398d915529bdffa6f600d6d17fe733d3210"),
        blockNumber = Some(BigInt(3740614)),
        transactionHash = Some("0xea6649db0f88d5400159853bf2c5b752ce724435dfb85b35f8725cf4cdc1ad6d"),
        transactionPosition = Some(BigInt(2))),
      Trace(
        action = Trace.Action.RewardAction(author = "0xb8d2ac822f3d0445f5b83d32b0b176c2cb3d0e60", value = "0x0", rewardType = "reward"),
        error = None,
        result = None,
        subtraces = BigInt(0),
        traceAddress = List(BigInt(0)),
        `type` = "reward",
        blockHash = Some("0x8d9aff92ab07598b65ca3457dc68ee66de48ea0e1d4b0a658b6e39977b2a8542"),
        blockNumber = Some(BigInt(5676554)),
        transactionHash = None,
        transactionPosition = None),
    )
    val info = FullTraceInfo("0x", None, Some(traces), None)
    assert(response.get.fullTraceInfo == info)
  }


  test("ParityFullTraceResponseVMTrace") {
    web3sServiceResponse.buildResponse(
      "{\n"
        + "    \"jsonrpc\": \"2.0\",\n"
        + "    \"result\": {\n"
        + "        \"output\": \"0x\",\n"
        + "        \"vmTrace\": {\n"
        + "            \"code\": \"0x6060604052361561004a576000357c01\",\n"
        + "            \"ops\": [{\n"
        + "                    \"cost\": 20000,\n"
        + "                    \"ex\": {\n"
        + "                        \"mem\": null,\n"
        + "                        \"push\": [\n"
        + "\n"
        + "                        ],\n"
        + "                        \"store\": {\n"
        + "                            \"key\": \"0x0\",\n"
        + "                            \"val\": \"0x14772e4f805b4dd2e69bd6d3f9b5edf0dfa5385a\"\n"
        + "                        },\n"
        + "                        \"used\": 241835\n"
        + "                    },\n"
        + "                    \"pc\": 79,\n"
        + "                    \"sub\": null\n"
        + "                }, {\n"
        + "                    \"cost\": 9700,\n"
        + "                    \"ex\": {\n"
        + "                        \"mem\": {\n"
        + "                            \"data\": \"0x\",\n"
        + "                            \"off\": 96\n"
        + "                        },\n"
        + "                        \"push\": [\n"
        + "                            \"0x1\"\n"
        + "                        ],\n"
        + "                        \"store\": null,\n"
        + "                        \"used\": 317494\n"
        + "                    },\n"
        + "                    \"pc\": 337,\n"
        + "                    \"sub\": {\n"
        + "                        \"code\": \"0x606060405236156100b75763ffffffff\",\n"
        + "                        \"ops\": [{\n"
        + "                                \"cost\": 3,\n"
        + "                                \"ex\": {\n"
        + "                                    \"mem\": null,\n"
        + "                                    \"push\": [\n"
        + "                                        \"0x60\"\n"
        + "                                    ],\n"
        + "                                    \"store\": null,\n"
        + "                                    \"used\": 5753235\n"
        + "                                },\n"
        + "                                \"pc\": 0,\n"
        + "                                \"sub\": null\n"
        + "                            }\n"
        + "                        ]\n"
        + "                    }\n"
        + "                }\n"
        + "            ]\n"
        + "        }\n"
        + "    },\n"
        + "    \"id\": 1\n"
        + "}")
    val response = trace.traceRawTransaction("", Nil)
    val vmtrace = VMTrace(
      "0x6060604052361561004a576000357c01",
      List(
        VMOperation(
          sub = None,
          cost = BigInt(20000),
          ex = Ex(mem = None, push = Nil, store = Some(Store("0x0", "0x14772e4f805b4dd2e69bd6d3f9b5edf0dfa5385a")), used = BigInt(241835)),
          pc = BigInt(79)
        ),
        VMOperation(
          sub = Some(
            VMTrace(
              "0x606060405236156100b75763ffffffff",
              List(
                VMOperation(
                  sub = None,
                  cost = BigInt(3),
                  ex = Ex(mem = None, push = List("0x60"), store = None, used = BigInt(5753235)),
                  pc = BigInt(0)
                )
              )
            )),
          cost = BigInt(9700),
          ex = Ex(mem = Some(Mem("0x", BigInt(96))), push = List("0x1"), store = None, used = BigInt(317494)),
          pc = BigInt(337)
        )
      )
    )
    val info = FullTraceInfo("0x", None, None, Some(vmtrace))
    assert(response.get.fullTraceInfo == info)
  }
