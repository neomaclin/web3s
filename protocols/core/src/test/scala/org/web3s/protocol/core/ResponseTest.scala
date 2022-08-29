package org.web3s.protocol.core

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.protocol.core.methods.response.*
import org.web3s.utils.Numeric
import org.web3s.Web3sEthereum
import org.web3s.protocol.core.Web3sServiceResponseJsonTest
import org.web3s.protocol.core.methods.request.{EthFilter, FilterTopic, SingleTopic, Transaction}

import scala.util.Try

class ResponseTest extends AnyFunSuite :


  private val web3sServiceResponse = new Web3sServiceResponseJsonTest
  val web3sEthereum: Ethereum[Try] = new Web3sEthereum(web3sServiceResponse)
  test("Web3ClientVersion") {
    web3sServiceResponse.buildResponse("""{"id":67,"jsonrpc":"2.0","result": "Mist/v0.9.3/darwin/go1.4.1"}""")
    val response = web3sEthereum.web3ClientVersion
    assert(response.get.web3ClientVersion == "Mist/v0.9.3/darwin/go1.4.1")
  }

  test("Web3Sha3") {
    web3sServiceResponse.buildResponse("""{"id":64,"jsonrpc":"2.0","result": "0x47173285a8d7341e5e972fc677286384f802f8ef42a5ec5f03bbfa254cb01fad"}""")
    val response = web3sEthereum.web3Sha3("")
    assert(response.get.result == "0x47173285a8d7341e5e972fc677286384f802f8ef42a5ec5f03bbfa254cb01fad")
  }

  test("NetVersion") {
    web3sServiceResponse.buildResponse("""{"id":67,"jsonrpc":"2.0","result": "59"}""")
    val response = web3sEthereum.netVersion
    assert(response.get.netVersion == "59")
  }


  test("NetListening") {
    web3sServiceResponse.buildResponse("""{"id":67,"jsonrpc":"2.0","result": true}""")
    val response = web3sEthereum.netListening
    assert(response.get.isListening)
  }

  test("NetPeerCount") {
    web3sServiceResponse.buildResponse("""{"id":67,"jsonrpc":"2.0","result": "0x2"}""")
    val response = web3sEthereum.netPeerCount
    assert(response.get.quantity == BigInt(2))
  }

  test("AdminNodeInfo") {
    web3sServiceResponse.buildResponse("{\n"
      + "    \"jsonrpc\": \"2.0\",\n"
      + "    \"id\": 1,\n"
      + "    \"result\": {\n"
      + "        \"id\": \"8ae75d6795f3541f897bcbfd3b4551aaf78b932cd0e91bf75a273940375c12a3\",\n"
      + "        \"name\": \"Geth/v1.9.6-stable-bd059680/linux-amd64/go1.13.1\",\n"
      + "        \"enode\": \"enode://1672a190f8c67669590db4b094c87573cbbc9b12f63d7137f505cfaa2cd2d35bea61abe1f8c898db4eab01d6c901270d7fff601b97a78f79ccefd83016b315cc@127.0.0.1:30303\",\n"
      + "        \"enr\": \"enr:-Jq4QCKylmBZEJ1xizokiKyEST7FUrrOESva-sFWTkbBY6J0Xco6eUOkoc7lGOHy6yyCnjWhBEd35dr-c1FRxE3ozUEEg2V0aMrJhMs6ZLuDD8wlgmlkgnY0gmlwhH8AAAGJc2VjcDI1NmsxoQIWcqGQ-MZ2aVkNtLCUyHVzy7ybEvY9cTf1Bc-qLNLTW4N0Y3CCdl-DdWRwgnZf\",\n"
      + "        \"ip\": \"127.0.0.1\",\n"
      + "        \"ports\": {\n"
      + "            \"discovery\": 30303,\n"
      + "            \"listener\": 30303\n"
      + "        },\n"
      + "        \"listenAddr\": \"[::]:30303\",\n"
      + "        \"protocols\": {\n"
      + "            \"eth\": {\n"
      + "                \"network\": 4,\n"
      + "                \"difficulty\": 1,\n"
      + "                \"genesis\": \"0x6341fd3daf94b748c72ced5a5b26028f2474f5f00d824504e4fa37a75767e177\",\n"
      + "				   \"consensus\": \"clique\",\n"
      + "                \"config\": {\n"
      + "                    \"chainId\": 4,\n"
      + "                    \"homesteadBlock\": 1,\n"
      + "                    \"daoForkSupport\": true,\n"
      + "                    \"eip150Block\": 2,\n"
      + "                    \"eip150Hash\": \"0x9b095b36c15eaf13044373aef8ee0bd3a382a5abb92e402afa44b8249c3a90e9\",\n"
      + "                    \"eip155Block\": 3,\n"
      + "                    \"eip158Block\": 3,\n"
      + "                    \"byzantiumBlock\": 1035301,\n"
      + "                    \"constantinopleBlock\": 3660663,\n"
      + "                    \"petersburgBlock\": 4321234,\n"
      + "                    \"istanbulBlock\": 5435345,\n"
      + "                    \"clique\": {\n"
      + "                        \"period\": 15,\n"
      + "                        \"epoch\": 30000\n"
      + "                    }\n"
      + "                },\n"
      + "                \"head\": \"0x6341fd3daf94b748c72ced5a5b26028f2474f5f00d824504e4fa37a75767e177\"\n"
      + "            }\n"
      + "        }\n"
      + "    }\n"
      + "}")
    val response = web3sEthereum.adminNodeInfo
    assert(response.get.result.name == "Geth/v1.9.6-stable-bd059680/linux-amd64/go1.13.1")
  }

  test("EthProtocolVersion") {
    web3sServiceResponse.buildResponse("""{"id":67,"jsonrpc":"2.0","result": "54"}""")
    val response = web3sEthereum.ethProtocolVersion
    assert(response.get.protocolVersion == "54")
  }


  test("EthSyncingInProgress") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": {\n"
      + "  \"startingBlock\": \"0x384\",\n"
      + "  \"currentBlock\": \"0x386\",\n"
      + "  \"highestBlock\": \"0x454\"\n"
      + "  }\n"
      + "}")
    val response = web3sEthereum.ethSyncing
    assert(response.get.result == EthSyncing.Result(Some("0x384"), Some("0x386"), Some("0x454"), None, None))
  }


  test("EthSyncing") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": false\n"
      + "}")
    val response = web3sEthereum.ethSyncing
    assert(!response.get.isSyncing)
  }


  test("EthMining") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": true\n"
      + "}")
    val response = web3sEthereum.ethMining
    assert(response.get.isMining)
  }


  test("EthHashrate") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":71,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": \"0x38a\"\n"
      + "}")
    val response = web3sEthereum.ethHashrate
    assert(response.get.hashrate == BigInt(906))
  }

  test("EthGasPrice") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":73,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": \"0x9184e72a000\"\n"
      + "}")
    val response = web3sEthereum.ethGasPrice
    assert(response.get.gasPrice == BigInt(10000000000000L))
  }

  test("EthAccounts") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": [\"0x407d73d8a49eeb85d32cf465507dd71d507100c1\"]\n"
      + "}")
    val response = web3sEthereum.ethAccounts
    assert(response.get.accounts == List("0x407d73d8a49eeb85d32cf465507dd71d507100c1"))
  }


  test("EthBlockNumber") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":83,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": \"0x4b7\"\n"
      + "}")
    val response = web3sEthereum.ethBlockNumber
    assert(response.get.blockNumber == BigInt(1207L))
  }

  test("EthGetBalance") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": \"0x234c8a3397aab58\"\n"
      + "}")
    val response = web3sEthereum.ethGetBalance("", DefaultBlockParameterName.LATEST)
    assert(response.get.balance == BigInt(158972490234375000L))
  }


  test("EthStorageAt") {
    web3sServiceResponse.buildResponse("{\n"
      + "    \"jsonrpc\":\"2.0\","
      + "    \"id\":1,"
      + "    \"result\":"
      + "\"0x000000000000000000000000000000000000000000000000000000000000162e\""
      + "}")
    val response = web3sEthereum.ethGetStorageAt("", BigInt(0), DefaultBlockParameterName.LATEST)
    assert(response.get.data == "0x000000000000000000000000000000000000000000000000000000000000162e")
  }


  test("EthGetTransactionCount") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": \"0x1\"\n"
      + "}")
    val response = web3sEthereum.ethGetTransactionCount("", DefaultBlockParameterName.LATEST)
    assert(response.get.transactionCount == BigInt(1))
  }

  test("EthGetBlockTransactionCountByHash") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": \"0xb\"\n"
      + "}")
    val response = web3sEthereum.ethGetBlockTransactionCountByHash("")
    assert(response.get.transactionCount == BigInt(11))
  }


  test("EthGetBlockTransactionCountByNumber") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": \"0xa\"\n"
      + "}")
    val response = web3sEthereum.ethGetBlockTransactionCountByNumber(DefaultBlockParameterName.LATEST)
    assert(response.get.transactionCount == BigInt(10))
  }

  test("EthGetUncleCountByBlockHash") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": \"0x1\"\n"
      + "}")
    val response = web3sEthereum.ethGetUncleCountByBlockHash("")
    assert(response.get.uncleCount == BigInt(1))
  }


  test("EthGetUncleCountByBlockNumber") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": \"0x1\"\n"
      + "}")
    val response = web3sEthereum.ethGetUncleCountByBlockNumber(DefaultBlockParameterName.LATEST)
    assert(response.get.uncleCount == BigInt(1))
  }


  test("GetCode") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": \"0x600160008035811a818181146012578301005b601b60013560255"
      + "65b8060005260206000f25b600060078202905091905056\"\n"
      + "}")
    val response = web3sEthereum.ethGetCode("", DefaultBlockParameterName.LATEST)
    assert(response.get.code == "0x600160008035811a818181146012578301005b601b6001356025565b8060005260206000f25b600060078202905091905056")
  }


  test("EthSign") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": \"0x600160008035811a818181146012578301005b601b60013560255"
      + "65b8060005260206000f25b600060078202905091905056\"\n"
      + "}")
    val response = web3sEthereum.ethGetCode("", DefaultBlockParameterName.LATEST)
    assert(response.get.code == "0x600160008035811a818181146012578301005b601b6001356025565b8060005260206000f25b600060078202905091905056")
  }

  test("EthSendTransaction") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": \"0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331\"}")
    val response = web3sEthereum.ethSendTransaction(
      Transaction(
        from = "",
        data = "")
    )
    assert(response.get.transactionHash == "0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331")
  }


  test("EthSendRawTransaction") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": \"0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331\"}")
    val response = web3sEthereum.ethSendRawTransaction("")
    assert(response.get.transactionHash == "0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331")
  }

  test("EthCall") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": \"0x\"}")
    val response = web3sEthereum.ethCall(Transaction(
      from = "",
      data = "")
      , DefaultBlockParameterName.LATEST)
    assert(response.get.value == "0x")
    assert(!response.get.isReverted)
    assert(response.get.revertReason.isEmpty)
  }

  //
  //  test("EthCallReverted") {
  //    web3sServiceResponse.buildResponse("{\n"
  //      + "  \"id\":1,\n"
  //      + "  \"jsonrpc\": \"2.0\",\n"
  //      + "  \"result\": \"0x08c379a0"
  //      + "0000000000000000000000000000000000000000000000000000000000000020"
  //      + "00000000000000000000000000000000000000000000000000000000000000ee"
  //      + "536f6c696469747920757365732073746174652d726576657274696e67206578"
  //      + "63657074696f6e7320746f2068616e646c65206572726f72732e205468652072"
  //      + "6571756972652066756e6374696f6e2073686f756c6420626520757365642074"
  //      + "6f20656e737572652076616c696420636f6e646974696f6e732c207375636820"
  //      + "617320696e707574732c206f7220636f6e747261637420737461746520766172"
  //      + "6961626c657320617265206d65742c206f7220746f2076616c69646174652072"
  //      + "657475726e2076616c7565732066726f6d2063616c6c7320746f206578746572"
  //      + "6e616c20636f6e7472616374732e000000000000000000000000000000000000\"\n"
  //      + "}")
  //    val response = web3sEthereum.ethSendRawTransaction("")
  //    assert(response.get.transactionHash == "0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331")
  //  }


  test("EthEstimateGas") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": \"0x5208\"}")
    val response = web3sEthereum.ethEstimateGas(Transaction(from = "", data = ""))
    assert(response.get.amountUsed == BigInt(21000))
  }


  test("EthBlockTransactionHashes") {
    web3sServiceResponse.buildResponse("{\n"
      + "\"id\":1,\n"
      + "\"jsonrpc\":\"2.0\",\n"
      + "\"result\": {\n"
      + "    \"number\": \"0x1b4\",\n"
      + "    \"hash\": \"0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331\",\n"
      + "    \"parentHash\": \"0x9646252be9520f6e71339a8df9c55e4d7619deeb018d2a3f2d21fc165dde5eb5\",\n"
      + "    \"nonce\": \"0xe04d296d2460cfb8472af2c5fd05b5a214109c25688d3704aed5484f9a7792f2\",\n"
      + "    \"sha3Uncles\": \"0x1dcc4de8dec75d7aab85b567b6ccd41ad312451b948a7413f0a142fd40d49347\",\n"
      + "    \"logsBloom\": \"0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331\",\n"
      + "    \"transactionsRoot\": \"0x56e81f171bcc55a6ff8345e692c0f86e5b48e01b996cadc001622fb5e363b421\",\n"
      + "    \"stateRoot\": \"0xd5855eb08b3387c0af375e9cdb6acfc05eb8f519e419b874b6ff2ffda7ed1dff\",\n"
      + "    \"receiptsRoot\": \"0x56e81f171bcc55a6ff8345e692c0f86e5b48e01b996cadc001622fb5e363b421\",\n"
      + "    \"author\": \"0x1a95ad5ccdb0677af951810c6ddf4935afe4e5a6\",\n"
      + "    \"miner\": \"0x4e65fda2159562a496f9f3522f89122a3088497a\",\n"
      + "    \"mixHash\": \"0x57919c4e72e79ad7705a26e7ecd5a08ff546ac4fa37882e9cc57be87a3dab26b\",\n"
      + "    \"difficulty\": \"0x027f07\",\n"
      + "    \"totalDifficulty\":  \"0x027f07\",\n"
      + "    \"extraData\": \"0x0000000000000000000000000000000000000000000000000000000000000000\",\n"
      + "    \"size\":  \"0x027f07\",\n"
      + "    \"gasLimit\": \"0x9f759\",\n"
      + "    \"gasUsed\": \"0x9f759\",\n"
      + "    \"timestamp\": \"0x54e34e8e\",\n"
      + "    \"transactions\": ["
      + "        \"0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331\",\n"
      + "        \"0xd5855eb08b3387c0af375e9cdb6acfc05eb8f519e419b874b6ff2ffda7ed1df\"\n"
      + "    ], \n"
      + "    \"uncles\": [\n"
      + "       \"0x1dcc4de8dec75d7aab85b567b6ccd41ad312451b948a7413f0a142fd40d49347\",\n"
      + "       \"0xd5855eb08b3387c0af375e9cdb6acfc05eb8f519e419b874b6ff2ffda7ed1dff\"\n"
      + "    ],\n"
      + "    \"sealFields\": [\n"
      + "       \"0x57919c4e72e79ad7705a26e7ecd5a08ff546ac4fa37882e9cc57be87a3dab26b\",\n"
      + "       \"0x39a3eb432fbef1fc\"\n"
      + "    ],\n"
      + "    \"baseFeePerGas\": \"0x7\"\n"
      + "  }\n"
      + "}")
    val expectedBlock = EthBlock.Block(
      "0x1b4",
      "0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331",
      "0x9646252be9520f6e71339a8df9c55e4d7619deeb018d2a3f2d21fc165dde5eb5",
      "0xe04d296d2460cfb8472af2c5fd05b5a214109c25688d3704aed5484f9a7792f2",
      "0x1dcc4de8dec75d7aab85b567b6ccd41ad312451b948a7413f0a142fd40d49347",
      "0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331",
      "0x56e81f171bcc55a6ff8345e692c0f86e5b48e01b996cadc001622fb5e363b421",
      "0xd5855eb08b3387c0af375e9cdb6acfc05eb8f519e419b874b6ff2ffda7ed1dff",
      "0x56e81f171bcc55a6ff8345e692c0f86e5b48e01b996cadc001622fb5e363b421",
      "0x1a95ad5ccdb0677af951810c6ddf4935afe4e5a6",
      "0x4e65fda2159562a496f9f3522f89122a3088497a",
      "0x57919c4e72e79ad7705a26e7ecd5a08ff546ac4fa37882e9cc57be87a3dab26b",
      "0x027f07",
      "0x027f07",
      "0x0000000000000000000000000000000000000000000000000000000000000000",
      "0x027f07",
      "0x9f759",
      "0x9f759",
      "0x54e34e8e",
      List("0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331",
        "0xd5855eb08b3387c0af375e9cdb6acfc05eb8f519e419b874b6ff2ffda7ed1df"),
      List(
        "0x1dcc4de8dec75d7aab85b567b6ccd41ad312451b948a7413f0a142fd40d49347",
        "0xd5855eb08b3387c0af375e9cdb6acfc05eb8f519e419b874b6ff2ffda7ed1dff"),
      List(
        "0x57919c4e72e79ad7705a26e7ecd5a08ff546ac4fa37882e9cc57be87a3dab26b",
        "0x39a3eb432fbef1fc"),
      "0x7")

    val response = web3sEthereum.ethGetBlockByHash("", false)
    assert(response.get.block.contains(expectedBlock))
  }

  test("EthBlockFullTransactionsParity") {
    web3sServiceResponse.buildResponse("{\n"
      + "\"id\":1,\n"
      + "\"jsonrpc\":\"2.0\",\n"
      + "\"result\": {\n"
      + "    \"number\": \"0x1b4\",\n"
      + "    \"hash\": \"0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331\",\n"
      + "    \"parentHash\": \"0x9646252be9520f6e71339a8df9c55e4d7619deeb018d2a3f2d21fc165dde5eb5\",\n"
      + "    \"nonce\": \"0xe04d296d2460cfb8472af2c5fd05b5a214109c25688d3704aed5484f9a7792f2\",\n"
      + "    \"sha3Uncles\": \"0x1dcc4de8dec75d7aab85b567b6ccd41ad312451b948a7413f0a142fd40d49347\",\n"
      + "    \"logsBloom\": \"0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331\",\n"
      + "    \"transactionsRoot\": \"0x56e81f171bcc55a6ff8345e692c0f86e5b48e01b996cadc001622fb5e363b421\",\n"
      + "    \"stateRoot\": \"0xd5855eb08b3387c0af375e9cdb6acfc05eb8f519e419b874b6ff2ffda7ed1dff\",\n"
      + "    \"receiptsRoot\": \"0x56e81f171bcc55a6ff8345e692c0f86e5b48e01b996cadc001622fb5e363b421\",\n"
      + "    \"author\": \"0x1a95ad5ccdb0677af951810c6ddf4935afe4e5a6\",\n"
      + "    \"miner\": \"0x4e65fda2159562a496f9f3522f89122a3088497a\",\n"
      + "    \"mixHash\": \"0x57919c4e72e79ad7705a26e7ecd5a08ff546ac4fa37882e9cc57be87a3dab26b\",\n"
      + "    \"difficulty\": \"0x027f07\",\n"
      + "    \"totalDifficulty\":  \"0x027f07\",\n"
      + "    \"extraData\": \"0x0000000000000000000000000000000000000000000000000000000000000000\",\n"
      + "    \"size\":  \"0x027f07\",\n"
      + "    \"gasLimit\": \"0x9f759\",\n"
      + "    \"gasUsed\": \"0x9f759\",\n"
      + "    \"timestamp\": \"0x54e34e8e\",\n"
      + "    \"transactions\": [{"
      + "        \"hash\":\"0xc6ef2fc5426d6ad6fd9e2a26abeab0aa2411b7ab17f30a99d3cb96aed1d1055b\",\n"
      + "        \"nonce\":\"0x\",\n"
      + "        \"blockHash\": \"0xbeab0aa2411b7ab17f30a99d3cb9c6ef2fc5426d6ad6fd9e2a26a6aed1d1055b\",\n"
      + "        \"blockNumber\": \"0x15df\",\n"
      + "        \"transactionIndex\":  \"0x1\",\n"
      + "        \"from\":\"0x407d73d8a49eeb85d32cf465507dd71d507100c1\",\n"
      + "        \"to\":\"0x85h43d8a49eeb85d32cf465507dd71d507100c1\",\n"
      + "        \"value\":\"0x7f110\",\n"
      + "        \"gas\": \"0x7f110\",\n"
      + "        \"gasPrice\":\"0x09184e72a000\",\n"
      + "        \"input\":\"0x603880600c6000396000f300603880600c6000396000f3603880600c6000396000f360\","
      + "        \"creates\":null,\n"
      + "        \"publicKey\":\"0x6614d7d7bfe989295821985de0439e868b26ff05f98ae0da0ce5bccc24ea368a083b785323c9fcb405dd4c10a2c95d93312a1b2d68beb24ab4ea7c3c2f7c455b\",\n"
      + "        \"raw\":\"0xf8cd83103a048504a817c800830e57e0945927c5cc723c4486f93bf90bad3be8831139499e80b864140f8dd300000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000c03905df347aa6490d5a98fbb8d8e49520000000000000000000000000000000000000000000000000000000057d56ee61ba0f115cc4d7516dd430046504e1c888198e0323e8ded016d755f89c226ba3481dca04a2ae8ee49f1100b5c0202b37ed8bacf4caeddebde6b7f77e12e7a55893e9f62\",\n"
      + "        \"r\":\"0xf115cc4d7516dd430046504e1c888198e0323e8ded016d755f89c226ba3481dc\",\n"
      + "        \"s\":\"0x4a2ae8ee49f1100b5c0202b37ed8bacf4caeddebde6b7f77e12e7a55893e9f62\",\n"
      + "        \"v\":\"0\",\n"
      + "    \"accessList\": [{"
      + "        \"address\":\"0x408e41876cccdc0f92210600ef50372656052a38\",\n"
      + "    \"storageKeys\": ["
      + "        \"0x18919546fd5421b0ef1b1b8dfce80500e69f2e28ae34c4d6298172949fa77dcc\",\n"
      + "        \"0x4869ff95a61ee1ded0b22e2d0e3f54f3199886a9f361e634132c95164bfc5129\"\n"
      + "    ] \n"
      + "    }], \n"
      + "        \"type\":\"0x0\",\n"
      + "        \"maxFeePerGas\": \"0x7f110\",\n"
      + "        \"maxPriorityFeePerGas\": \"0x7f110\"\n"
      + "    }], \n"
      + "    \"uncles\": [\n"
      + "       \"0x1dcc4de8dec75d7aab85b567b6ccd41ad312451b948a7413f0a142fd40d49347\",\n"
      + "       \"0xd5855eb08b3387c0af375e9cdb6acfc05eb8f519e419b874b6ff2ffda7ed1dff\"\n"
      + "    ],\n"
      + "    \"sealFields\": [\n"
      + "       \"0x57919c4e72e79ad7705a26e7ecd5a08ff546ac4fa37882e9cc57be87a3dab26b\",\n"
      + "       \"0x39a3eb432fbef1fc\"\n"
      + "    ],\n"
      + "    \"baseFeePerGas\": \"0x7\"\n"
      + "  }\n"
      + "}")
    val expectedBlock = EthBlock.Block(
      "0x1b4",
      "0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331",
      "0x9646252be9520f6e71339a8df9c55e4d7619deeb018d2a3f2d21fc165dde5eb5",
      "0xe04d296d2460cfb8472af2c5fd05b5a214109c25688d3704aed5484f9a7792f2",
      "0x1dcc4de8dec75d7aab85b567b6ccd41ad312451b948a7413f0a142fd40d49347",
      "0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331",
      "0x56e81f171bcc55a6ff8345e692c0f86e5b48e01b996cadc001622fb5e363b421",
      "0xd5855eb08b3387c0af375e9cdb6acfc05eb8f519e419b874b6ff2ffda7ed1dff",
      "0x56e81f171bcc55a6ff8345e692c0f86e5b48e01b996cadc001622fb5e363b421",
      "0x1a95ad5ccdb0677af951810c6ddf4935afe4e5a6",
      "0x4e65fda2159562a496f9f3522f89122a3088497a",
      "0x57919c4e72e79ad7705a26e7ecd5a08ff546ac4fa37882e9cc57be87a3dab26b",
      "0x027f07",
      "0x027f07",
      "0x0000000000000000000000000000000000000000000000000000000000000000",
      "0x027f07",
      "0x9f759",
      "0x9f759",
      "0x54e34e8e",
      List(
        EthTransaction.Transaction(
          hash = "0xc6ef2fc5426d6ad6fd9e2a26abeab0aa2411b7ab17f30a99d3cb96aed1d1055b",
          nonce = "0x",
          blockHash = "0xbeab0aa2411b7ab17f30a99d3cb9c6ef2fc5426d6ad6fd9e2a26a6aed1d1055b",
          blockNumber = "0x15df",
          transactionIndex = "0x1",
          from = "0x407d73d8a49eeb85d32cf465507dd71d507100c1",
          to = "0x85h43d8a49eeb85d32cf465507dd71d507100c1",
          value = "0x7f110",
          gasPrice = "0x09184e72a000",
          gas = "0x7f110",
          input = "0x603880600c6000396000f300603880600c6000396000f3603880600c6000396000f360",
          creates = None,
          publicKey = "0x6614d7d7bfe989295821985de0439e868b26ff05f98ae0da0ce5bccc24ea368a083b785323c9fcb405dd4c10a2c95d93312a1b2d68beb24ab4ea7c3c2f7c455b",
          raw = "0xf8cd83103a048504a817c800830e57e0945927c5cc723c4486f93bf90bad3be8831139499e80b864140f8dd300000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000c03905df347aa6490d5a98fbb8d8e49520000000000000000000000000000000000000000000000000000000057d56ee61ba0f115cc4d7516dd430046504e1c888198e0323e8ded016d755f89c226ba3481dca04a2ae8ee49f1100b5c0202b37ed8bacf4caeddebde6b7f77e12e7a55893e9f62",
          r = "0xf115cc4d7516dd430046504e1c888198e0323e8ded016d755f89c226ba3481dc",
          s = "0x4a2ae8ee49f1100b5c0202b37ed8bacf4caeddebde6b7f77e12e7a55893e9f62",
          v = 0L,
          `type` = "0x0",
          maxFeePerGas = "0x7f110",
          maxPriorityFeePerGas = "0x7f110",
          accessList = List(
            EthTransaction.AccessListObject("0x408e41876cccdc0f92210600ef50372656052a38",
              List(
                "0x18919546fd5421b0ef1b1b8dfce80500e69f2e28ae34c4d6298172949fa77dcc",
                "0x4869ff95a61ee1ded0b22e2d0e3f54f3199886a9f361e634132c95164bfc5129"))
          ))),
      List(
        "0x1dcc4de8dec75d7aab85b567b6ccd41ad312451b948a7413f0a142fd40d49347",
        "0xd5855eb08b3387c0af375e9cdb6acfc05eb8f519e419b874b6ff2ffda7ed1dff")
      ,
      List(
        "0x57919c4e72e79ad7705a26e7ecd5a08ff546ac4fa37882e9cc57be87a3dab26b",
        "0x39a3eb432fbef1fc")
      ,
      "0x7"
    )

    val response = web3sEthereum.ethGetBlockByHash("", false)
    assert(response.get.block.contains(expectedBlock))
  }


  test("EthBlockNull") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": null}")
    val response = web3sEthereum.ethGetBlockByHash("", false)
    assert(response.get.block.isEmpty)
  }

  test("EthTransaction") {
    web3sServiceResponse.buildResponse("{\n"
      + "    \"id\":1,\n"
      + "    \"jsonrpc\":\"2.0\",\n"
      + "    \"result\": {\n"
      + "        \"hash\":\"0xc6ef2fc5426d6ad6fd9e2a26abeab0aa2411b7ab17f30a99d3cb96aed1d1055b\",\n"
      + "        \"nonce\":\"0x\",\n"
      + "        \"blockHash\": \"0xbeab0aa2411b7ab17f30a99d3cb9c6ef2fc5426d6ad6fd9e2a26a6aed1d1055b\",\n"
      + "        \"blockNumber\": \"0x15df\",\n"
      + "        \"transactionIndex\":  \"0x1\",\n"
      + "        \"from\":\"0x407d73d8a49eeb85d32cf465507dd71d507100c1\",\n"
      + "        \"to\":\"0x85h43d8a49eeb85d32cf465507dd71d507100c1\",\n"
      + "        \"value\":\"0x7f110\",\n"
      + "        \"gas\": \"0x7f110\",\n"
      + "        \"gasPrice\":\"0x09184e72a000\",\n"
      + "        \"input\":\"0x603880600c6000396000f300603880600c6000396000f3603880600c6000396000f360\",\n"
      + "        \"creates\":null,\n"
      + "        \"publicKey\":\"0x6614d7d7bfe989295821985de0439e868b26ff05f98ae0da0ce5bccc24ea368a083b785323c9fcb405dd4c10a2c95d93312a1b2d68beb24ab4ea7c3c2f7c455b\",\n"
      + "        \"raw\":\"0xf8cd83103a048504a817c800830e57e0945927c5cc723c4486f93bf90bad3be8831139499e80b864140f8dd300000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000c03905df347aa6490d5a98fbb8d8e49520000000000000000000000000000000000000000000000000000000057d56ee61ba0f115cc4d7516dd430046504e1c888198e0323e8ded016d755f89c226ba3481dca04a2ae8ee49f1100b5c0202b37ed8bacf4caeddebde6b7f77e12e7a55893e9f62\",\n"
      + "        \"r\":\"0xf115cc4d7516dd430046504e1c888198e0323e8ded016d755f89c226ba3481dc\",\n"
      + "        \"s\":\"0x4a2ae8ee49f1100b5c0202b37ed8bacf4caeddebde6b7f77e12e7a55893e9f62\",\n"
      + "        \"v\":\"0\",\n"
      + "    \"accessList\": [{"
      + "        \"address\":\"0x408e41876cccdc0f92210600ef50372656052a38\",\n"
      + "    \"storageKeys\": ["
      + "        \"0x18919546fd5421b0ef1b1b8dfce80500e69f2e28ae34c4d6298172949fa77dcc\",\n"
      + "        \"0x4869ff95a61ee1ded0b22e2d0e3f54f3199886a9f361e634132c95164bfc5129\"\n"
      + "    ] \n"
      + "    }], \n"
      + "        \"type\":\"0x0\",\n"
      + "        \"maxFeePerGas\": \"0x7f110\",\n"
      + "        \"maxPriorityFeePerGas\": \"0x7f110\"\n"
      + "  }\n"
      + "}")
    val expectedTransaction = EthTransaction.Transaction(
      hash = "0xc6ef2fc5426d6ad6fd9e2a26abeab0aa2411b7ab17f30a99d3cb96aed1d1055b",
      nonce = "0x",
      blockHash = "0xbeab0aa2411b7ab17f30a99d3cb9c6ef2fc5426d6ad6fd9e2a26a6aed1d1055b",
      blockNumber = "0x15df",
      transactionIndex = "0x1",
      from = "0x407d73d8a49eeb85d32cf465507dd71d507100c1",
      to = "0x85h43d8a49eeb85d32cf465507dd71d507100c1",
      value = "0x7f110",
      gas = "0x7f110",
      gasPrice = "0x09184e72a000",
      input = "0x603880600c6000396000f300603880600c6000396000f3603880600c6000396000f360",
      creates = None,
      publicKey = "0x6614d7d7bfe989295821985de0439e868b26ff05f98ae0da0ce5bccc24ea368a083b785323c9fcb405dd4c10a2c95d93312a1b2d68beb24ab4ea7c3c2f7c455b",
      raw = "0xf8cd83103a048504a817c800830e57e0945927c5cc723c4486f93bf90bad3be8831139499e80b864140f8dd300000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000c03905df347aa6490d5a98fbb8d8e49520000000000000000000000000000000000000000000000000000000057d56ee61ba0f115cc4d7516dd430046504e1c888198e0323e8ded016d755f89c226ba3481dca04a2ae8ee49f1100b5c0202b37ed8bacf4caeddebde6b7f77e12e7a55893e9f62",
      r = "0xf115cc4d7516dd430046504e1c888198e0323e8ded016d755f89c226ba3481dc",
      s = "0x4a2ae8ee49f1100b5c0202b37ed8bacf4caeddebde6b7f77e12e7a55893e9f62",
      v = 0,
      `type` = "0x0",
      maxFeePerGas = "0x7f110",
      maxPriorityFeePerGas = "0x7f110",
      List(
        EthTransaction.AccessListObject(
          "0x408e41876cccdc0f92210600ef50372656052a38",
          List(
            "0x18919546fd5421b0ef1b1b8dfce80500e69f2e28ae34c4d6298172949fa77dcc",
            "0x4869ff95a61ee1ded0b22e2d0e3f54f3199886a9f361e634132c95164bfc5129")))
    )

    val response = web3sEthereum.ethGetTransactionByHash("")
    assert(response.get.transaction.contains(expectedTransaction))
    assert(expectedTransaction.copy(v = 0x25 ).chainIdEncoded == 1L)
    assert(expectedTransaction.copy(v = 0x4A817C823L).chainIdEncoded == 10000000000L)
  }


  test("EthTransactionNull") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": null}")
    val response = web3sEthereum.ethGetTransactionByHash("")
    assert(response.get.transaction.isEmpty)
  }
//
//test("EthGetTransactionReceiptBeforeByzantium") {
//  web3sServiceResponse.buildResponse("{\n"
//    + "  \"id\":1,\n"
//    + "  \"jsonrpc\": \"2.0\",\n"
//    + "  \"result\": null}")
//  val response = web3sEthereum.ethGetTransactionByHash("")
//  assert(response.get.transaction.isEmpty)
//}
//
//test("EthGetTransactionReceiptAfterByzantium") {
//  web3sServiceResponse.buildResponse("{\n"
//    + "  \"id\":1,\n"
//    + "  \"jsonrpc\": \"2.0\",\n"
//    + "  \"result\": null}")
//  val response = web3sEthereum.ethGetTransactionByHash("")
//  assert(response.get.transaction.isEmpty)
//}
//
//test("TransactionReceiptIsStatusOK") {
//  web3sServiceResponse.buildResponse("{\n"
//    + "  \"id\":1,\n"
//    + "  \"jsonrpc\": \"2.0\",\n"
//    + "  \"result\": null}")
//  val response = web3sEthereum.ethGetTransactionByHash("")
//  assert(response.get.transaction.isEmpty)
//}


  test("EthGetCompilers") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": [\"solidity\", \"lll\", \"serpent\"]\n"
      + "}")
    val response = web3sEthereum.ethGetCompilers
    assert(response.get.compilers == List("solidity", "lll", "serpent"))
  }

//test("EthCompileSolidity") {
//  web3sServiceResponse.buildResponse("{\n"
//    + "  \"id\":1,\n"
//    + "  \"jsonrpc\": \"2.0\",\n"
//    + "  \"result\": [\"solidity\", \"lll\", \"serpent\"]\n"
//    + "}")
//  val response = web3sEthereum.ethGetCompilers
//  assert(response.get.compilers == List("solidity", "lll", "serpent"))
//}

  test("EthCompileLLL") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": \"0x603880600c6000396000f3006001600060e060020a60003504806"
      + "3c6888fa114601857005b6021600435602b565b8060005260206000f35b600081600702"
      + "905091905056\"\n"
      + "}")
    val response = web3sEthereum.ethCompileLLL("")
    assert(response.get.compiledSourceCode == "0x603880600c6000396000f3006001600060e060020a600035048063c6888fa114601857005b60" + "21600435602b565b8060005260206000f35b600081600702905091905056")
  }


  test("EthCompileSerpent") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": \"0x603880600c6000396000f3006001600060e060020a60003504806"
      + "3c6888fa114601857005b6021600435602b565b8060005260206000f35b600081600702"
      + "905091905056\"\n"
      + "}")
    val response = web3sEthereum.ethCompileSerpent("")
    assert(response.get.compiledSourceCode == "0x603880600c6000396000f3006001600060e060020a600035048063c6888fa114601857005b60" + "21600435602b565b8060005260206000f35b600081600702905091905056")
  }

  test("EthFilter") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": \"0x1\"\n"
      + "}")
    val response = web3sEthereum.ethNewBlockFilter
    assert(response.get.filterId == BigInt(1))
  }

  test("EthUninstallFilter") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": true\n"
      + "}")
    val response = web3sEthereum.ethUninstallFilter(BigInt(1))
    assert(response.get.isUninstalled)
  }

//  test("EthLog") {
//    web3sServiceResponse.buildResponse("{\n"
//      + "  \"id\":1,\n"
//      + "  \"jsonrpc\": \"2.0\",\n"
//      + "  \"result\": true\n"
//      + "}")
//    val response = web3sEthereum.ethUninstallFilter(BigInt(1))
//    assert(response.get.isUninstalled)
//  }


  test("EthGetWork") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\":\"2.0\",\n"
      + "  \"result\": [\n"
      + "      \"0x1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdef\",\n"
      + "      \"0x5EED00000000000000000000000000005EED0000000000000000000000000000\",\n"
      + "      \"0xd1ff1c01710000000000000000000000d1ff1c01710000000000000000000000\"\n"
      + "    ]\n"
      + "}")
    val response = web3sEthereum.ethGetWork
    assert(response.get.currentBlockHeaderPowHash == "0x1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdef")
    assert(response.get.seedHashForDag == "0x5EED00000000000000000000000000005EED0000000000000000000000000000")
    assert(response.get.boundaryCondition == "0xd1ff1c01710000000000000000000000d1ff1c01710000000000000000000000")
  }


  test("EthSubmitWork") {
    web3sServiceResponse.buildResponse(
      "{\n"
        + "  \"id\":1,\n"
        + "  \"jsonrpc\":\"2.0\",\n"
        + "  \"result\": true\n"
        + "}")
    val response = web3sEthereum.ethSubmitWork("","","")
    assert(response.get.isSolutionValid)
  }


  test("EthSubmitHashrate") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": true\n"
      + "}")
    val response = web3sEthereum.ethSubmitHashrate("","")
    assert(response.get.isSubmissionSuccessful)
  }


  test("DbPutString") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": true\n"
      + "}")
    val response = web3sEthereum.dbPutString("","","")
    assert(response.get.isValueStored)
  }

  test("DbGetString") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": \"myString\"\n"
      + "}")
    val response = web3sEthereum.dbGetString("","")
    assert(response.get.storedValue == "myString")
  }


  test("DbPutHex") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": true\n"
      + "}")
    val response = web3sEthereum.dbPutHex("","","")
    assert(response.get.isValueStored)
  }

  test("DbGetHex") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": \"0x68656c6c6f20776f726c64\"\n"
      + "}")
    val response = web3sEthereum.dbGetHex("","")
    assert(response.get.storedValue == "0x68656c6c6f20776f726c64")
  }



  test("BooleanResponse") {
    web3sServiceResponse.buildResponse("{\n"
      + "  \"id\":1,\n"
      + "  \"jsonrpc\": \"2.0\",\n"
      + "  \"result\": true\n"
      + "}")
    val response = web3sEthereum.adminAddPeer("")
    assert(response.get.isSuccess)
  }

  test("AdminDataDir") {
    import org.web3s.protocol.core.methods.response.admin.*
    web3sServiceResponse.buildResponse("{\n"
      + "    \"jsonrpc\":\"2.0\",\n"
      + "    \"id\":22,\n"
      + "    \"result\":\"sampleDir\"\n"
      + "}")
    val response = web3sEthereum.adminDataDir
    assert(response.get.dataDir == "sampleDir")
  }

  test("TxPoolStatus") {
    web3sServiceResponse.buildResponse("{\n"
      + "    \"jsonrpc\":\"2.0\",\n"
      + "    \"id\":22,\n"
      + "    \"result\":{ \"pending\": \"10\",\n"
      + "			\"queued\": \"7\"}\n"
      + "}")
    val response = web3sEthereum.txPoolStatus
    assert(response.get.pending == 10)
    assert(response.get.queued == 7)
  }