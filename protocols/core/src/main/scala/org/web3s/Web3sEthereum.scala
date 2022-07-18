package org.web3s

import cats.effect.Async
import org.web3s.protocol.core.*
import org.web3s.protocol.core.methods.response.*
import org.web3s.protocol.core.methods.response.admin.*
import org.web3s.services.Web3sService

class Web3sEthereum[F[_] : Async](services: Web3sService[F]) extends Ethereum[F]:
  override def web3ClientVersion: F[Web3ClientVersion] =
    services.send(Request(method = "web3_clientVersion")).map(Web3ClientVersion.apply)

  override def web3Sha3(data: String): F[Web3Sha3] =
    services.send(Request(method = "web3_sha3", params = List(data))).map(Web3Sha3.apply)

  override def netVersion: F[NetVersion] =
    services.send(Request(method = "net_version")).map(NetVersion.apply)

  override def netListening: F[NetListening] =
    services.send(Request(method = "net_listening")).map(NetListening.apply)

  override def netPeerCount: F[NetPeerCount] =
    services.send(Request(method = "net_peerCount")).map(NetPeerCount.apply)

  override def adminNodeInfo: F[AdminNodeInfo] =
    services.send(Request(method = "admin_nodeInfo")).map(AdminNodeInfo.apply)

  override def adminPeers: F[AdminPeers] =
    services.send(Request(method = "admin_peers")).map(AdminPeers.apply)

  override def adminAddPeer(url: String): F[BooleanResponse] =
    services.send(Request(method = "admin_addPeer", params = List(url))).map(BooleanResponse.apply)

  override def adminRemovePeer(url: String): F[BooleanResponse] =
    services.send(Request(method = "admin_removePeer", params = List(url))).map(BooleanResponse.apply)

  override def adminDataDir: F[AdminDataDir] =
    services.send(Request(method = "admin_datadir")).map(AdminDataDir.apply)

  override def ethProtocolVersion: F[EthProtocolVersion] =
    services.send(Request(method = "eth_protocolVersion")).map(EthProtocolVersion.apply)

  override def ethChainId: F[EthChainId] =
    services.send(Request(method = "eth_chainId")).map(EthChainId.apply)

  override def ethCoinbase: F[EthCoinbase] =
    services.send(Request(method = "eth_coinbase")).map(EthCoinbase.apply)

  override def ethSyncing: F[EthSyncing] =
    services.send(Request(method = "eth_syncing")).map(EthSyncing.apply)

  override def ethMining: F[EthMining] =
    services.send(Request(method = "eth_mining")).map(EthMining.apply)

  override def ethHashrate: F[EthHashrate] =
    services.send(Request(method = "eth_hashrate")).map(EthHashrate.apply)

  override def ethGasPrice: F[EthGasPrice] =
    services.send(Request(method = "eth_gasPrice")).map(EthGasPrice.apply)

  override def ethMaxPriorityFeePerGas: F[EthMaxPriorityFeePerGas] =
    services.send(Request(method = "eth_maxPriorityFeePerGas")).map(EthMaxPriorityFeePerGas.apply)

  override def ethFeeHistory(blockCount: Int, newestBlock: DefaultBlockParameter, rewardPercentiles: List[Double]): F[EthFeeHistory] =
    services.send(Request(method = "eth_feeHistory")).map(EthFeeHistory.apply)

  override def ethAccounts: F[EthAccounts] =
    services.send(Request(method = "eth_accounts")).map(EthAccounts.apply)

  override def ethBlockNumber: F[EthBlockNumber] =
    services.send(Request(method = "eth_blockNumber")).map(EthBlockNumber.apply)

  override def ethGetBalance(address: String, defaultBlockParameter: DefaultBlockParameter): F[EthGetBalance] =
    services.send(Request(method = "eth_getBalance")).map(EthGetBalance.apply)

  override def ethGetStorageAt(address: String, position: BigInt, defaultBlockParameter: DefaultBlockParameter): F[EthGetStorageAt] =
    services.send(Request(method = "eth_getStorageAt")).map(EthGetStorageAt.apply)

  override def ethGetTransactionCount(address: String, defaultBlockParameter: DefaultBlockParameter): F[EthGetTransactionCount] =
    services.send(Request(method = "eth_getTransactionCount")).map(EthGetTransactionCount.apply)


  override def ethGetBlockTransactionCountByHash(blockHash: String): F[EthGetBlockTransactionCountByHash] =
    services.send(Request(method = "eth_getBlockTransactionCountByHash")).map(EthGetBlockTransactionCountByHash.apply)


  override def ethGetBlockTransactionCountByNumber(defaultBlockParameter: DefaultBlockParameter): F[EthGetBlockTransactionCountByNumber] =
    services.send(Request(method = "eth_getBlockTransactionCountByNumber")).map(EthGetBlockTransactionCountByNumber.apply)


  override def ethGetUncleCountByBlockHash(blockHash: String): F[EthGetUncleCountByBlockHash] =
    services.send(Request(method = "eth_getUncleCountByBlockHash")).map(EthGetUncleCountByBlockHash.apply)


  override def ethGetUncleCountByBlockNumber(defaultBlockParameter: DefaultBlockParameter): F[EthGetUncleCountByBlockNumber] =
    services.send(Request(method = "eth_getUncleCountByBlockNumber")).map(EthGetUncleCountByBlockNumber.apply)


  override def ethGetCode(address: String, defaultBlockParameter: DefaultBlockParameter): F[EthGetCode] =
    services.send(Request(method = "eth_getCode")).map(EthGetCode.apply)


  override def ethSign(address: String, sha3HashOfDataToSign: String): F[EthSign] =
    services.send(Request(method = "eth_sign")).map(EthSign.apply)


  override def ethSendTransaction(transaction: Transaction): F[EthSendTransaction] =
    services.send(Request(method = "eth_sendTransaction")).map(EthSendTransaction.apply)


  override def ethSendRawTransaction(signedTransactionData: String): F[EthSendTransaction] =
    services.send(Request(method = "eth_sendRawTransaction")).map(EthSendTransaction.apply)


  override def ethCall(transaction: Transaction, defaultBlockParameter: DefaultBlockParameter): F[EthCall] =
    services.send(Request(method = "eth_call")).map(EthCall.apply)


  override def ethEstimateGas(transaction: Transaction): F[EthEstimateGas] =
    services.send(Request(method = "eth_estimateGas")).map(EthEstimateGas.apply)


  override def ethGetBlockByHash(blockHash: String, returnFullTransactionObjects: Boolean): F[EthBlock] =
    services.send(Request(method = "eth_getBlockByHash")).map(EthBlock.apply)


  override def ethGetBlockByNumber(defaultBlockParameter: DefaultBlockParameter, returnFullTransactionObjects: Boolean): F[EthBlock] =
    services.send(Request(method = "eth_getBlockByNumber")).map(EthGasPrice.apply)


  override def ethGetTransactionByHash(transactionHash: String): F[EthTransaction] =
    services.send(Request(method = "eth_getTransactionByHash")).map(EthGasPrice.apply)


  override def ethGetTransactionByBlockHashAndIndex(blockHash: String, transactionIndex: BigInt): F[EthTransaction] =
    services.send(Request(method = "eth_gasPrice")).map(EthGasPrice.apply)


  override def ethGetTransactionByBlockNumberAndIndex(defaultBlockParameter: DefaultBlockParameter, transactionIndex: BigInt): F[EthTransaction] =
    services.send(Request(method = "eth_getTransactionByBlockHashAndIndex")).map(EthGasPrice.apply)


  override def ethGetTransactionReceipt(transactionHash: String): F[EthGetTransactionReceipt] =
    services.send(Request(method = "eth_getTransactionReceipt")).map(EthGetTransactionReceipt.apply)


  override def ethGetUncleByBlockHashAndIndex(blockHash: String, transactionIndex: BigInt): F[EthBlock] =
    services.send(Request(method = "eth_getUncleByBlockHashAndIndex")).map(EthBlock.apply)


  override def ethGetUncleByBlockNumberAndIndex(defaultBlockParameter: DefaultBlockParameter, transactionIndex: BigInt): F[EthBlock] =
    services.send(Request(method = "eth_getUncleByBlockNumberAndIndex")).map(EthGasPrice.apply)


  override def ethGetCompilers: F[EthGetCompilers] =
    services.send(Request(method = "eth_getCompilers")).map(EthGetCompilers.apply)


  override def ethCompileLLL(sourceCode: String): F[EthCompileLLL] =
    services.send(Request(method = "eth_compileLLL")).map(EthCompileLLL.apply)


  override def ethCompileSolidity(sourceCode: String): F[EthCompileSolidity] =
    services.send(Request(method = "eth_compileSolidity")).map(EthCompileSolidity.apply)


  override def ethCompileSerpent(sourceCode: String): F[EthCompileSerpent] =
    services.send(Request(method = "eth_compileSerpent")).map(EthCompileSerpent.apply)


  override def ethNewFilter(ethFilter: EthFilter): F[EthFilter] =
    services.send(Request(method = "eth_newFilter")).map(EthFilter.apply)


  override def ethNewBlockFilter: F[EthFilter] =
    services.send(Request(method = "eth_newBlockFilter")).map(EthFilter.apply)


  override def ethNewPendingTransactionFilter: F[EthFilter] =
    services.send(Request(method = "eth_newPendingTransactionFilter")).map(EthFilter.apply)


  override def ethUninstallFilter(filterId: BigInt): F[EthUninstallFilter] =
    services.send(Request(method = "eth_uninstallFilter")).map(EthUninstallFilter.apply)


  override def ethGetFilterChanges(filterId: BigInt): F[EthLog] =
    services.send(Request(method = "eth_getFilterChanges")).map(EthLog.apply)


  override def ethGetFilterLogs(filterId: BigInt): F[EthLog] =
    services.send(Request(method = "eth_getFilterLogs")).map(EthLog.apply)


  override def ethGetLogs(ethFilter: EthFilter): F[EthLog] =
    services.send(Request(method = "eth_getLogs")).map(EthLog.apply)


  override def ethGetWork: F[EthGetWork] =
    services.send(Request(method = "eth_getWork")).map(EthGetWork.apply)


  override def ethSubmitWork(nonce: String, headerPowHash: String, mixDigest: String): F[EthSubmitWork] =
    services.send(Request(method = "eth_submitWork")).map(EthSubmitWork.apply)


  override def ethSubmitHashrate(hashrate: String, clientId: String): F[EthSubmitHashrate] =
    services.send(Request(method = "eth_submitHashrate")).map(EthSubmitHashrate.apply)


  override def dbPutString(databaseName: String, keyName: String, stringToStore: String): F[DbPutString] =
    services.send(Request(method = "db_putString")).map(DbPutString.apply)


  override def dbGetString(databaseName: String, keyName: String): F[DbGetString] =
    services.send(Request(method = "db_getString")).map(DbGetString.apply)


  override def dbPutHex(databaseName: String, keyName: String, dataToStore: String): F[DbPutHex] =
    services.send(Request(method = "db_putHex")).map(DbPutHex.apply)


  override def dbGetHex(databaseName: String, keyName: String): F[DbGetHex] =
    services.send(Request(method = "db_getHex")).map(DbGetHex.apply)


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

  override def txPoolStatus: F[TxPoolStatus] =
    services.send(Request(method = "txpool_status")).map(TxPoolStatus.apply)