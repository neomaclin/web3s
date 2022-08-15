package org.web3s.protocol.core

import eu.timepit.refined.numeric.NonNegative
import org.scalatest.funsuite.AnyFunSuite
import org.web3s.utils.Numeric
import org.web3s.Web3sEthereum
import org.web3s.protocol.core.Web3sServiceRequestJsonTest
import org.web3s.services.Web3sService
import org.web3s.protocol.core.methods.request.Transaction
import eu.timepit.refined._
import eu.timepit.refined.auto._
import eu.timepit.refined.numeric._
import eu.timepit.refined.generic._
import scala.util.Try

class RequestTest extends AnyFunSuite :

  import io.circe._
  import io.circe.syntax._
  import io.circe.generic.auto._
  import io.circe.parser._

  private val web3sServiceRequestJsonTest = new Web3sServiceRequestJsonTest
  val web3sEthereum: Ethereum[Try] = new Web3sEthereum(web3sServiceRequestJsonTest)
  test("Web3ClientVersion") {
    web3sEthereum.web3ClientVersion
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"web3_clientVersion","params":[],"id":1}""")
  }

  test("Web3Sha3") {
    web3sEthereum.web3Sha3("0x68656c6c6f20776f726c64")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"web3_sha3","params":["0x68656c6c6f20776f726c64"],"id":1}""")
  }

  test("NetVersion") {
    web3sEthereum.netVersion
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"net_version","params":[],"id":1}""")
  }

  test("NetListening") {
    web3sEthereum.netListening
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"net_listening","params":[],"id":1}""")
  }

  test("NetPeerCount") {
    web3sEthereum.netPeerCount
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"net_peerCount","params":[],"id":1}""")
  }

  test("AdminNodeInfo") {
    web3sEthereum.adminNodeInfo
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"admin_nodeInfo","params":[],"id":1}""")
  }

  test("AdminAddPeer") {
    web3sEthereum.adminAddPeer("url")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"admin_addPeer","params":["url"],"id":1}""")
  }

  test("AdminRemovePeer") {
    web3sEthereum.adminRemovePeer("url")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"admin_removePeer","params":["url"],"id":1}""")
  }


  test("AdminDataDir") {
    web3sEthereum.adminDataDir
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"admin_datadir","params":[],"id":1}""")
  }


  test("EthProtocolVersion") {
    web3sEthereum.ethProtocolVersion
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_protocolVersion","params":[],"id":1}""")
  }


  test("EthSyncing") {
    web3sEthereum.ethSyncing
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_syncing","params":[],"id":1}""")
  }


  test("EthCoinbase") {
    web3sEthereum.ethCoinbase
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_coinbase","params":[],"id":1}""")
  }


  test("EthMining") {
    web3sEthereum.ethMining
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_mining","params":[],"id":1}""")
  }


  test("EthHashrate") {
    web3sEthereum.ethHashrate
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_hashrate","params":[],"id":1}""")
  }


  test("EthGasPrice") {
    web3sEthereum.ethGasPrice
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_gasPrice","params":[],"id":1}""")
  }


  test("EthMaxPriorityFeePerGas") {
    web3sEthereum.ethMaxPriorityFeePerGas
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_maxPriorityFeePerGas","params":[],"id":1}""")
  }


  test("EthFeeHistory") {
    web3sEthereum.ethFeeHistory(1, DefaultBlockParameterName.LATEST, Nil)
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_feeHistory","params":[1,"latest",null],"id":1}""")
  }


  test("EthAccounts") {
    web3sEthereum.ethAccounts
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_accounts","params":[],"id":1}""")
  }


  test("EthBlockNumber") {
    web3sEthereum.ethBlockNumber
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_blockNumber","params":[],"id":1}""")
  }


  test("EthGetBalance") {
    web3sEthereum.ethGetBalance(
      "0x407d73d8a49eeb85d32cf465507dd71d507100c1",
      DefaultBlockParameterName.LATEST)
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_getBalance","params":["0x407d73d8a49eeb85d32cf465507dd71d507100c1","latest"],"id":1}""")
  }


  test("EthGetStorageAt") {
    web3sEthereum.ethGetStorageAt(
      "0x295a70b2de5e3953354a6a8344e616ed314d7251",
      BigInt(0),
      DefaultBlockParameterName.LATEST)
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_getStorageAt","params":["0x295a70b2de5e3953354a6a8344e616ed314d7251","0x0","latest"],"id":1}""")
  }


  test("EthGetTransactionCount") {
    web3sEthereum.ethGetTransactionCount(
      "0x407d73d8a49eeb85d32cf465507dd71d507100c1",
      DefaultBlockParameterName.LATEST)
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_getTransactionCount","params":["0x407d73d8a49eeb85d32cf465507dd71d507100c1","latest"],"id":1}""")
  }


  test("EthGetBlockTransactionCountByHash") {
    web3sEthereum.ethGetBlockTransactionCountByHash(
      "0xb903239f8543d04b5dc1ba6579132b143087c68db1b2168786408fcbce568238")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_getBlockTransactionCountByHash","params":["0xb903239f8543d04b5dc1ba6579132b143087c68db1b2168786408fcbce568238"],"id":1}""")
  }

  test("EthGetBlockTransactionCountByNumber") {
    web3sEthereum.ethGetBlockTransactionCountByNumber(DefaultBlockParameter.valueOf(refineV[NonNegative].unsafeFrom(Numeric.toBigInt("0xe8"))))
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_getBlockTransactionCountByNumber","params":["0xe8"],"id":1}""")
  }


  test("EthGetCode") {
    web3sEthereum.ethGetCode(
      "0xa94f5374fce5edbc8e2a8697c15331677e6ebf0b",
      DefaultBlockParameter.valueOf(refineV[NonNegative].unsafeFrom(Numeric.toBigInt("0x2"))))
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_getCode","params":["0xa94f5374fce5edbc8e2a8697c15331677e6ebf0b","0x2"],"id":1}""")
  }

  test("EthSign") {
    web3sEthereum.ethSign("0x8a3106a3e50576d4b6794a0e74d3bb5f8c9acaab", "0xc5d2460186f7233c927e7db2dcc703c0e500b653ca82273b7bfad8045d85a470")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_sign","params":["0x8a3106a3e50576d4b6794a0e74d3bb5f8c9acaab","0xc5d2460186f7233c927e7db2dcc703c0e500b653ca82273b7bfad8045d85a470"],"id":1}""")
  }

  test("EthSendTransaction") {

    web3sEthereum.ethSendTransaction(
      Transaction(
        from = "0xb60e8dd61c5d32be8058bb8eb970870f07233155",
        nonce = Some(BigInt(1)),
        gasPrice = Some(Numeric.toBigInt("0x9184e72a000")),
        gas = Some(Numeric.toBigInt("0x76c0")),
        to = Some("0xb60e8dd61c5d32be8058bb8eb970870f07233155"),
        value = Some(Numeric.toBigInt("0x9184e72a")),
        data = "0xd46e8dd67c5d32be8d46e8dd67c5d32be8058bb8eb970870f072445675058bb8eb970870f072445675")
    )
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_sendTransaction","params":[{"from":"0xb60e8dd61c5d32be8058bb8eb970870f07233155","to":"0xb60e8dd61c5d32be8058bb8eb970870f07233155","gas":"0x76c0","gasPrice":"0x9184e72a000","value":"0x9184e72a","data":"0xd46e8dd67c5d32be8d46e8dd67c5d32be8058bb8eb970870f072445675058bb8eb970870f072445675","nonce":"0x1"}],"id":1}""")
  }

  test("EthSendRawTransaction") {
    web3sEthereum.ethSendRawTransaction(
      "0xd46e8dd67c5d32be8d46e8dd67c5d32be8058bb8eb970870f072445675058bb8eb970870f072445675")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_sendRawTransaction","params":["0xd46e8dd67c5d32be8d46e8dd67c5d32be8058bb8eb970870f072445675058bb8eb970870f072445675"],"id":1}""")
  }

  test("EthCall") {
    web3sEthereum.ethCall(
      Transaction.createEthCallTransaction(
        from = "0xa70e8dd61c5d32be8058bb8eb970870f07233155",
        to = "0xb60e8dd61c5d32be8058bb8eb970870f07233155",
        data = "0x0"),
      DefaultBlockParameter.valueOf("latest"))
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_call","params":[{"from":"0xa70e8dd61c5d32be8058bb8eb970870f07233155","to":"0xb60e8dd61c5d32be8058bb8eb970870f07233155","data":"0x0"},"latest"],"id":1}""")
  }
  test("EthEstimateGas") {
    web3sEthereum.ethEstimateGas(
      Transaction.createEthCallTransaction(
        "0xa70e8dd61c5d32be8058bb8eb970870f07233155",
        "0x52b93c80364dc2dd4444c146d73b9836bbbb2b3f",
        "0x0"))
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_estimateGas","params":[{"from":"0xa70e8dd61c5d32be8058bb8eb970870f07233155","to":"0x52b93c80364dc2dd4444c146d73b9836bbbb2b3f","data":"0x0"}],"id":1}""")
  }
  test("EthEstimateGasContractCreation") {
    web3sEthereum.ethEstimateGas(
      Transaction.createContractTransaction(
        "0x52b93c80364dc2dd4444c146d73b9836bbbb2b3f",
        BigInt(1),
        BigInt(10),
        ""))
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_estimateGas","params":[{"from":"0x52b93c80364dc2dd4444c146d73b9836bbbb2b3f","gasPrice":"0xa","data":"0x","nonce":"0x1"}],"id":1}""")
  }
  test("EthGetBlockByHash") {
    web3sEthereum.ethGetBlockByHash(
      "0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331", true)
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_getBlockByHash","params":["0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331",true],"id":1}""")
  }
  test("EthGetBlockByNumber") {
    web3sEthereum.ethGetBlockByNumber(DefaultBlockParameter.valueOf(refineV[NonNegative].unsafeFrom(Numeric.toBigInt("0x1b4"))), true)
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_getBlockByNumber","params":["0x1b4",true],"id":1}""")
  }
  test("EthGetTransactionByHash") {
    web3sEthereum.ethGetTransactionByHash(
      "0xb903239f8543d04b5dc1ba6579132b143087c68db1b2168786408fcbce568238")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_getTransactionByHash","params":["0xb903239f8543d04b5dc1ba6579132b143087c68db1b2168786408fcbce568238"],"id":1}""")
  }
  test("EthGetTransactionByBlockHashAndIndex") {
    web3sEthereum.ethGetTransactionByBlockHashAndIndex(
      "0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331",
      BigInt(0))
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_getTransactionByBlockHashAndIndex","params":["0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331","0x0"],"id":1}""")
  }
  test("EthGetTransactionByBlockNumberAndIndex") {
    web3sEthereum.ethGetTransactionByBlockNumberAndIndex(
      DefaultBlockParameter.valueOf(refineV[NonNegative].unsafeFrom(Numeric.toBigInt("0x29c"))), BigInt(0))
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_getTransactionByBlockNumberAndIndex","params":["0x29c","0x0"],"id":1}""")
  }

  test("EthGetTransactionReceipt") {
    web3sEthereum.ethGetTransactionReceipt(
      "0xb903239f8543d04b5dc1ba6579132b143087c68db1b2168786408fcbce568238")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_getTransactionReceipt","params":["0xb903239f8543d04b5dc1ba6579132b143087c68db1b2168786408fcbce568238"],"id":1}""")
  }
  
  test("EthGetUncleByBlockHashAndIndex") {
    web3sEthereum.ethGetUncleByBlockHashAndIndex(
      "0xc6ef2fc5426d6ad6fd9e2a26abeab0aa2411b7ab17f30a99d3cb96aed1d1055b",
      BigInt(0))
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_getUncleByBlockHashAndIndex","params":["0xc6ef2fc5426d6ad6fd9e2a26abeab0aa2411b7ab17f30a99d3cb96aed1d1055b","0x0"],"id":1}""")
  }
  
  test("EthGetUncleByBlockNumberAndIndex") {
    web3sEthereum.ethGetUncleByBlockNumberAndIndex(
      DefaultBlockParameter.valueOf(refineV[NonNegative].unsafeFrom(Numeric.toBigInt("0x29c"))), BigInt(0))
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_getUncleByBlockNumberAndIndex","params":["0x29c","0x0"],"id":1}""")
  }

  test("EthGetCompilers") {
    web3sEthereum.ethGetCompilers
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_getCompilers","params":[],"id":1}""")
  }

  test("EthCompileSolidity") {
    web3sEthereum.ethCompileSolidity(
      "contract test { function multiply(uint a) returns(uint d) {   return a * 7;   } }")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_compileSolidity","params":["contract test { function multiply(uint a) returns(uint d) {   return a * 7;   } }"],"id":1}""")
  }

  test("EthCompileLLL") {
    web3sEthereum.ethCompileLLL("(returnlll (suicide (caller)))")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_compileLLL","params":["(returnlll (suicide (caller)))"],"id":1}""")
  }

  test("EthCompileSerpent") {
    web3sEthereum.ethCompileSerpent("/* some serpent */")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_compileSerpent","params":["/* some serpent */"],"id":1}""")
  }


//  test("EthNewFilter") {
//    web3sEthereum.ethNewFilter(ethFilter)
//    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_getUncleByBlockNumberAndIndex","params":["0x29c","0x0"],"id":1}""")
//  }

  test("EthNewBlockFilter") {
    web3sEthereum.ethNewBlockFilter
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_newBlockFilter","params":[],"id":1}""")
  }

  test("EthUninstallFilter") {
    web3sEthereum.ethUninstallFilter(Numeric.toBigInt("0xb"))
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_uninstallFilter","params":["0xb"],"id":1}""")
  }


  test("EthGetFilterChanges") {
    web3sEthereum.ethGetFilterChanges(Numeric.toBigInt("0x16"))
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_getFilterChanges","params":["0x16"],"id":1}""")
  }

  test("testEthGetFilterLogs") {
    web3sEthereum.ethGetFilterLogs(Numeric.toBigInt("0x16"))
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_getFilterLogs","params":["0x16"],"id":1}""")
  }

//  test("testEthGetLogs") {
//    web3sEthereum.ethGetUncleByBlockNumberAndIndex(
//      DefaultBlockParameter.valueOf(refineV[NonNegative].unsafeFrom(Numeric.toBigInt("0x29c"))), BigInt(0))
//    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_getUncleByBlockNumberAndIndex","params":["0x29c","0x0"],"id":1}""")
//  }

//  test("EthGetLogsWithNumericBlockRange") {
//    web3sEthereum.ethGetUncleByBlockNumberAndIndex(
//      DefaultBlockParameter.valueOf(refineV[NonNegative].unsafeFrom(Numeric.toBigInt("0x29c"))), BigInt(0))
//    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_getUncleByBlockNumberAndIndex","params":["0x29c","0x0"],"id":1}""")
//  }


//  test("EthGetLogsWithBlockHash") {
//    web3sEthereum.ethGetUncleByBlockNumberAndIndex(
//      DefaultBlockParameter.valueOf(refineV[NonNegative].unsafeFrom(Numeric.toBigInt("0x29c"))), BigInt(0))
//    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_getUncleByBlockNumberAndIndex","params":["0x29c","0x0"],"id":1}""")
//  }

  test("EthGetWork") {
    web3sEthereum.ethGetWork
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_getWork","params":[],"id":1}""")
  }

  test("EthSubmitWork") {
    web3sEthereum.ethSubmitWork(
      "0x0000000000000001",
      "0x1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdef",
      "0xD1FE5700000000000000000000000000D1FE5700000000000000000000000000")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_submitWork","params":["0x0000000000000001","0x1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdef","0xD1FE5700000000000000000000000000D1FE5700000000000000000000000000"],"id":1}""")
  }


  test("EthSubmitHashRate") {
    web3sEthereum.ethSubmitHashrate(
      "0x0000000000000000000000000000000000000000000000000000000000500000",
      "0x59daa26581d0acd1fce254fb7e85952f4c09d0915afd33d3886cd914bc7d283c")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"eth_submitHashrate","params":["0x0000000000000000000000000000000000000000000000000000000000500000","0x59daa26581d0acd1fce254fb7e85952f4c09d0915afd33d3886cd914bc7d283c"],"id":1}""")
  }


  test("DbPutString") {
    web3sEthereum.dbPutString("testDB", "myKey", "myString")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"db_putString","params":["testDB","myKey","myString"],"id":1}""")
  }


  test("DbGetString") {
    web3sEthereum.dbGetString("testDB", "myKey")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"db_getString","params":["testDB","myKey"],"id":1}""")
  }


  test("DbPutHex") {
    web3sEthereum.dbPutHex("testDB", "myKey", "0x68656c6c6f20776f726c64")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"db_putHex","params":["testDB","myKey","0x68656c6c6f20776f726c64"],"id":1}""")
  }


  test("DbGetHex") {
    web3sEthereum.dbGetHex("testDB", "myKey")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"db_getHex","params":["testDB","myKey"],"id":1}""")
  }

  test("TxPoolStatus") {
    web3sEthereum.txPoolStatus
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"txpool_status","params":[],"id":1}""")
  }