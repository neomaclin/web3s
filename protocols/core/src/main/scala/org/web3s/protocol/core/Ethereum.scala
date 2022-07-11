package org.web3s.protocol.core

import org.web3s.protocol.core.methods.response.*
import org.web3s.protocol.core.methods.request.Transaction
/** Core Ethereum JSON-RPC API. */
trait Ethereum[F[_]] {
  
  def web3ClientVersion: F[Web3ClientVersion]

  def web3Sha3(data: String): F[Web3Sha3]

  def netVersion: F[NetVersion]

  //def netListening: F[NetListening]

  def netPeerCount: F[NetPeerCount]

  //def adminNodeInfo: F[AdminNodeInfo]

  //def adminPeers: F[AdminPeers]

  def adminAddPeer(url: String): F[BooleanResponse]

  def adminRemovePeer(url: String): F[BooleanResponse]

  //def adminDataDir: F[AdminDataDir]

  def ethProtocolVersion: F[EthProtocolVersion]

  def ethChainId: F[EthChainId]

  def ethCoinbase: F[EthCoinbase]

 // def ethSyncing: F[EthSyncing]

  def ethMining: F[EthMining]

  def ethHashrate: F[EthHashrate]

  def ethGasPrice: F[EthGasPrice]

  //def ethMaxPriorityFeePerGas: F[EthMaxPriorityFeePerGas]

  //def ethFeeHistory(blockCount: Int, newestBlock: DefaultBlockParameter, rewardPercentiles: List[Double]): F[EthFeeHistory]

  def ethAccounts: F[EthAccounts]

  def ethBlockNumber: F[EthBlockNumber]

  def ethGetBalance(address: String, defaultBlockParameter: DefaultBlockParameter): F[EthGetBalance]

  def ethGetStorageAt(address: String, position: BigInt, defaultBlockParameter: DefaultBlockParameter): F[EthGetStorageAt]

  def ethGetTransactionCount(address: String, defaultBlockParameter: DefaultBlockParameter): F[EthGetTransactionCount]

  def ethGetBlockTransactionCountByHash(blockHash: String): F[EthGetBlockTransactionCountByHash]

  def ethGetBlockTransactionCountByNumber(defaultBlockParameter: DefaultBlockParameter): F[EthGetBlockTransactionCountByNumber]

  def ethGetUncleCountByBlockHash(blockHash: String): F[EthGetUncleCountByBlockHash]

  def ethGetUncleCountByBlockNumber(defaultBlockParameter: DefaultBlockParameter): F[EthGetUncleCountByBlockNumber]

  def ethGetCode(address: String, defaultBlockParameter: DefaultBlockParameter): F[EthGetCode]

  def ethSign(address: String, sha3HashOfDataToSign: String): F[EthSign]

  def ethSendTransaction(transaction: Transaction): F[EthSendTransaction]

  def ethSendRawTransaction(signedTransactionData: String): F[EthSendTransaction]

  def ethCall(transaction: Transaction, defaultBlockParameter: DefaultBlockParameter): F[EthCall]

  def ethEstimateGas(transaction: Transaction): F[EthEstimateGas]

  //def ethGetBlockByHash(blockHash: String, returnFullTransactionObjects: Boolean): F[EthBlock]

  //def ethGetBlockByNumber(defaultBlockParameter: DefaultBlockParameter, returnFullTransactionObjects: Boolean): F[EthBlock]

  //def ethGetTransactionByHash(transactionHash: String): F[EthTransaction]

  //def ethGetTransactionByBlockHashAndIndex(blockHash: String, transactionIndex: BigInt): F[EthTransaction]

  //def ethGetTransactionByBlockNumberAndIndex(defaultBlockParameter: DefaultBlockParameter, transactionIndex: BigInt): F[EthTransaction]

  //def ethGetTransactionReceipt(transactionHash: String): F[EthGetTransactionReceipt]

  //def ethGetUncleByBlockHashAndIndex(blockHash: String, transactionIndex: BigInt): F[EthBlock]

  //def ethGetUncleByBlockNumberAndIndex(defaultBlockParameter: DefaultBlockParameter, transactionIndex: BigInt): F[EthBlock]

  def ethGetCompilers: F[EthGetCompilers]

  def ethCompileLLL(sourceCode: String): F[EthCompileLLL]

  //def ethCompileSolidity(sourceCode: String): F[EthCompileSolidity]

  def ethCompileSerpent(sourceCode: String): F[EthCompileSerpent]

  def ethNewFilter(ethFilter: EthFilter): F[EthFilter]

  def ethNewBlockFilter: F[EthFilter]

  def ethNewPendingTransactionFilter: F[EthFilter]

  def ethUninstallFilter(filterId: BigInt): F[EthUninstallFilter]

  //def ethGetFilterChanges(filterId: BigInt): F[EthLog]

  //def ethGetFilterLogs(filterId: BigInt): F[EthLog]

  //def ethGetLogs(ethFilter: EthFilter): F[EthLog]

  def ethGetWork: F[EthGetWork]

  def ethSubmitWork(nonce: String, headerPowHash: String, mixDigest: String): F[EthSubmitWork]

  def ethSubmitHashrate(hashrate: String, clientId: String): F[EthSubmitHashrate]

  def dbPutString(databaseName: String, keyName: String, stringToStore: String): F[DbPutString]

  def dbGetString(databaseName: String, keyName: String): F[DbGetString]

  def dbPutHex(databaseName: String, keyName: String, dataToStore: String): F[DbPutHex]

  def dbGetHex(databaseName: String, keyName: String): F[DbGetHex]

//  def shhPost(shhPost: ShhPost): F[ShhPost]
//
//  def shhVersion: F[ShhVersion]
//
//  def shhNewIdentity: F[ShhNewIdentity]
//
//  def shhHasIdentity(identityAddress: String): F[ShhHasIdentity]
//
//  def shhNewGroup: F[ShhNewGroup]
//
//  def shhAddToGroup(identityAddress: String): F[ShhAddToGroup]
//
//  def shhNewFilter(shhFilter: ShhFilter): F[ShhNewFilter]
//
//  def shhUninstallFilter(filterId: BigInt): F[ShhUninstallFilter]
//
//  def shhGetFilterChanges(filterId: BigInt): F[ShhMessages]
//
//  def shhGetMessages(filterId: BigInt): F[ShhMessages]

  def txPoolStatus: F[TxPoolStatus]
}
