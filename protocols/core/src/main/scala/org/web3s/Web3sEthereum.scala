package org.web3s


import cats.MonadThrow
import cats.syntax.functor.*
import org.web3s.protocol.admin.methods.response.TxPoolContent
import org.web3s.protocol.core.*
import org.web3s.utils.Numeric
import org.web3s.utils.EthBigInt
import org.web3s.utils.EthBigInt.*
import org.web3s.protocol.core.methods.request.Transaction
import org.web3s.protocol.core.methods.request.{EthFilter => EthFilterRequest}
import org.web3s.protocol.core.methods.response.model.TransactionReceipt
import org.web3s.protocol.core.methods.response.*
import org.web3s.protocol.core.methods.response.decoders.given
import org.web3s.protocol.core.methods.response.admin.*
import org.web3s.services.Web3sService

object Web3sEthereum:
  val DEFAULT_BLOCK_TIME = 15 * 1000
class Web3sEthereum[F[_] : MonadThrow](services: Web3sService[F]) extends Ethereum[F]:
  import io.circe._
  import io.circe.syntax._
  import io.circe.generic.auto._
  import org.web3s.protocol.core.methods.request.encoder.given

  override def web3ClientVersion: F[Web3ClientVersion] =
    val request = Request(method = "web3_clientVersion")
    services.fetch[String](request).map(Web3ClientVersion.apply)

  override def web3Sha3(data: String): F[Web3Sha3] =
    val request = Request(method = "web3_sha3", params = List(data.asJson))
    services.fetch[String](request).map(Web3Sha3.apply)

  override def netVersion: F[NetVersion] =
    val request = Request(method = "net_version")
    services.fetch[String](request).map(NetVersion.apply)

  override def netListening: F[NetListening] =
    val request = Request(method = "net_listening")
    services.fetch[Boolean](request).map(NetListening.apply)

  override def netPeerCount: F[NetPeerCount] =
    val request = Request(method = "net_peerCount")
    services.fetch[EthBigInt](request).map(NetPeerCount.apply)

  override def adminNodeInfo: F[AdminNodeInfo] =
    val request = Request(method = "admin_nodeInfo")
    services.fetch[AdminNodeInfo.NodeInfo](request).map(AdminNodeInfo.apply)

  override def adminPeers: F[AdminPeers] =
    val request = Request(method = "admin_peers")
    services.fetch[List[AdminPeers.Peer]](request).map(AdminPeers.apply)

  override def adminAddPeer(url: String): F[BooleanResponse] =
    val request = Request(method = "admin_addPeer", params = List(url.asJson))
    services.fetch[Boolean](request).map(BooleanResponse.apply)

  override def adminRemovePeer(url: String): F[BooleanResponse] =
    val request = Request(method = "admin_removePeer", params = List(url.asJson))
    services.fetch[Boolean](request).map(BooleanResponse.apply)

  override def adminDataDir: F[AdminDataDir] =
    val request = Request(method = "admin_datadir")
    services.fetch[String](request).map(AdminDataDir.apply)

  override def ethProtocolVersion: F[EthProtocolVersion] =
    val request = Request(method = "eth_protocolVersion")
    services.fetch[String](request).map(EthProtocolVersion.apply)

  override def ethChainId: F[EthChainId] =
    val request = Request(method = "eth_chainId")
    services.fetch[EthBigInt](request).map(EthChainId.apply)

  override def ethCoinbase: F[EthCoinbase] =
    val request = Request(method = "eth_coinbase")
    services.fetch[String](request).map(EthCoinbase.apply)

  override def ethSyncing: F[EthSyncing] =
    val request = Request(method = "eth_syncing")
    services.fetch[EthSyncing.Result](request).map(EthSyncing.apply)

  override def ethMining: F[EthMining] =
    val request = Request(method = "eth_mining")
    services.fetch[Boolean](request).map(EthMining.apply)

  override def ethHashrate: F[EthHashrate] =
    val request = Request(method = "eth_hashrate")
    services.fetch[EthBigInt](request).map(EthHashrate.apply)

  override def ethGasPrice: F[EthGasPrice] =
    val request = Request(method = "eth_gasPrice")
    services.fetch[EthBigInt](request).map(EthGasPrice.apply)

  override def ethMaxPriorityFeePerGas: F[EthMaxPriorityFeePerGas] =
    val request = Request(method = "eth_maxPriorityFeePerGas")
    services.fetch[EthBigInt](request).map(EthMaxPriorityFeePerGas.apply)

  override def ethFeeHistory(blockCount: Int, newestBlock: DefaultBlockParameter, rewardPercentiles: List[Double]): F[EthFeeHistory] =
    val rewards = if rewardPercentiles.isEmpty then Json.Null else rewardPercentiles.asJson
    val request = Request(method = "eth_feeHistory",params = List(blockCount.asJson, newestBlock.asJson, rewards))
    services.fetch[EthFeeHistory.FeeHistory](request).map(EthFeeHistory.apply)

  override def ethAccounts: F[EthAccounts] =
    val request = Request(method = "eth_accounts")
    services.fetch[Seq[String]](request).map(EthAccounts.apply)

  override def ethBlockNumber: F[EthBlockNumber] =
    val request = Request(method = "eth_blockNumber")
    services.fetch[EthBigInt](request).map(EthBlockNumber.apply)

  override def ethGetBalance(address: String, defaultBlockParameter: DefaultBlockParameter): F[EthGetBalance] =
    val params = List(address.asJson, defaultBlockParameter.asJson)
    val request = Request(method = "eth_getBalance",params)
    services.fetch[EthBigInt](request).map(EthGetBalance.apply)

  override def ethGetStorageAt(address: String, position: BigInt, defaultBlockParameter: DefaultBlockParameter): F[EthGetStorageAt] =
    val params = List(address.asJson, position.asJson, defaultBlockParameter.asJson)
    val request = Request(method = "eth_getStorageAt",params)
    services.fetch[String](request).map(EthGetStorageAt.apply)

  override def ethGetTransactionCount(address: String, defaultBlockParameter: DefaultBlockParameter): F[EthGetTransactionCount] =
    val params = List(address.asJson, defaultBlockParameter.asJson)
    val request = Request(method = "eth_getTransactionCount",params)
    services.fetch[String](request).map(EthGetTransactionCount.apply)

  override def ethGetBlockTransactionCountByHash(blockHash: String): F[EthGetBlockTransactionCountByHash] =
    val  request = Request(method = "eth_getBlockTransactionCountByHash",params = List(blockHash.asJson))
    services.fetch[EthBigInt](request).map(EthGetBlockTransactionCountByHash.apply)


  override def ethGetBlockTransactionCountByNumber(defaultBlockParameter: DefaultBlockParameter): F[EthGetBlockTransactionCountByNumber] =
    val  request = Request(method = "eth_getBlockTransactionCountByNumber",params = List(defaultBlockParameter.asJson))
    services.fetch[EthBigInt](request).map(EthGetBlockTransactionCountByNumber.apply)

  override def ethGetUncleCountByBlockHash(blockHash: String): F[EthGetUncleCountByBlockHash] =
    val  request = Request(method = "eth_getUncleCountByBlockHash",params = List(blockHash.asJson))
    services.fetch[EthBigInt](request).map(EthGetUncleCountByBlockHash.apply)

  override def ethGetUncleCountByBlockNumber(defaultBlockParameter: DefaultBlockParameter): F[EthGetUncleCountByBlockNumber] =
    val  request = Request(method = "eth_getUncleCountByBlockNumber",params = List(defaultBlockParameter.asJson))
    services.fetch[EthBigInt](request).map(EthGetUncleCountByBlockNumber.apply)


  override def ethGetCode(address: String, defaultBlockParameter: DefaultBlockParameter): F[EthGetCode] =
    val params = List(address.asJson, defaultBlockParameter.asJson)
    val request = Request(method = "eth_getCode", params)
    services.fetch[String](request).map(EthGetCode.apply)


  override def ethSign(address: String, sha3HashOfDataToSign: String): F[EthSign] =
    val params = List(address.asJson, sha3HashOfDataToSign.asJson)
    val request = Request(method = "eth_sign", params)
    services.fetch[String](request).map(EthSign.apply)


  override def ethSendTransaction(transaction: Transaction): F[EthSendTransaction] =
    val params = List(transaction.asJson.dropNullValues)
    val request = Request(method = "eth_sendTransaction", params)
    services.fetch[String](request).map(EthSendTransaction.apply)


  override def ethSendRawTransaction(signedTransactionData: String): F[EthSendTransaction] =
    val params = List(signedTransactionData.asJson)
    val request = Request(method = "eth_sendRawTransaction", params)
    services.fetch[String](request).map(EthSendTransaction.apply)


  override def ethCall(transaction: Transaction, defaultBlockParameter: DefaultBlockParameter): F[EthCall] =
    val params = List(transaction.asJson.dropNullValues,defaultBlockParameter.asJson)
    val request = Request(method = "eth_call", params)
    services.fetch[String](request).map(EthCall.apply)


  override def ethEstimateGas(transaction: Transaction): F[EthEstimateGas] =
    val params = List(transaction.asJson.dropNullValues)
    val request = Request(method = "eth_estimateGas", params)
    services.fetch[EthBigInt](request).map(EthEstimateGas.apply)

  override def ethGetBlockByHash(blockHash: String, returnFullTransactionObjects: Boolean): F[EthBlock] =
    import EthBlock._
    val params = List(blockHash.asJson,returnFullTransactionObjects.asJson )
    val request = Request(method = "eth_getBlockByHash", params)
    services.fetch[EthBlock.Block](request).map(EthBlock.apply)


  override def ethGetBlockByNumber(defaultBlockParameter: DefaultBlockParameter, returnFullTransactionObjects: Boolean): F[EthBlock] =
    import EthBlock._
    val params = List(defaultBlockParameter.asJson, returnFullTransactionObjects.asJson)
    val request = Request(method = "eth_getBlockByNumber", params)
    services.fetch[EthBlock.Block](request).map(EthBlock.apply)


  override def ethGetTransactionByHash(transactionHash: String): F[EthTransaction] =
    import EthTransaction._
    val params = List(transactionHash.asJson)
    val request = Request(method = "eth_getTransactionByHash", params)
    services.fetch[Option[EthTransaction.Transaction]](request).map(EthTransaction.apply)

  override def ethGetTransactionByBlockHashAndIndex(blockHash: String, transactionIndex: BigInt): F[EthTransaction] =
    import EthTransaction._
    val params = List(blockHash.asJson, transactionIndex.asJson)
    val request = Request(method = "eth_getTransactionByBlockHashAndIndex", params)
    services.fetch[Option[EthTransaction.Transaction]](request).map(EthTransaction.apply)


  override def ethGetTransactionByBlockNumberAndIndex(defaultBlockParameter: DefaultBlockParameter, transactionIndex: BigInt): F[EthTransaction] =
    import EthTransaction._
    val params = List(defaultBlockParameter.asJson, transactionIndex.asJson)
    val request = Request(method = "eth_getTransactionByBlockNumberAndIndex", params)
    services.fetch[Option[EthTransaction.Transaction]](request).map(EthTransaction.apply)


  override def ethGetTransactionReceipt(transactionHash: String): F[EthGetTransactionReceipt] =
    val params = List(transactionHash.asJson)
    val request = Request(method = "eth_getTransactionReceipt", params)
    services.fetch[Option[TransactionReceipt]](request).map(EthGetTransactionReceipt.apply)


  override def ethGetUncleByBlockHashAndIndex(blockHash: String, transactionIndex: BigInt): F[EthBlock] =
    val params = List(blockHash.asJson,transactionIndex.asJson)
    val request = Request(method = "eth_getUncleByBlockHashAndIndex", params)
    services.fetch[EthBlock.Block](request).map(EthBlock.apply)


  override def ethGetUncleByBlockNumberAndIndex(defaultBlockParameter: DefaultBlockParameter, transactionIndex: BigInt): F[EthBlock] =
    val params = List(defaultBlockParameter.asJson, transactionIndex.asJson)
    val request = Request(method = "eth_getUncleByBlockNumberAndIndex", params)
    services.fetch[EthBlock.Block](request).map(EthBlock.apply)


  override def ethGetCompilers: F[EthGetCompilers] =
    services.fetch[List[String]](Request(method = "eth_getCompilers")).map(EthGetCompilers.apply)


  override def ethCompileLLL(sourceCode: String): F[EthCompileLLL] =
    val request = Request(method = "eth_compileLLL", params = List(sourceCode.asJson))
    services.fetch[String](request).map(EthCompileLLL.apply)


  override def ethCompileSolidity(sourceCode: String): F[EthCompileSolidity] =
    import EthCompileSolidity._
    val request = Request(method = "eth_compileSolidity", params = List(sourceCode.asJson))
    services.fetch[Map[String, EthCompileSolidity.Code]](request).map(EthCompileSolidity.apply)


  override def ethCompileSerpent(sourceCode: String): F[EthCompileSerpent] =
    val request = Request(method = "eth_compileSerpent", params = List(sourceCode.asJson))
    services.fetch[String](request).map(EthCompileSerpent.apply)


  override def ethNewFilter(ethFilter: EthFilterRequest): F[EthFilter] =
    val params = List(ethFilter.asJson.dropNullValues)
    val request = Request(method = "eth_newFilter",params)
    services.fetch[EthBigInt](request).map(EthFilter.apply)


  override def ethNewBlockFilter: F[EthFilter] =
    services.fetch[EthBigInt](Request(method = "eth_newBlockFilter")).map(EthFilter.apply)


  override def ethNewPendingTransactionFilter: F[EthFilter] =
    services.fetch[EthBigInt](Request(method = "eth_newPendingTransactionFilter")).map(EthFilter.apply)


  override def ethUninstallFilter(filterId: BigInt): F[EthUninstallFilter] =
    services.fetch[Boolean](Request(method = "eth_uninstallFilter",params = List(filterId.asJson))).map(EthUninstallFilter.apply)


  override def ethGetFilterChanges(filterId: BigInt): F[EthLog] =
    services.fetch[List[EthLog.LogResult]](Request(method = "eth_getFilterChanges",params = List(filterId.asJson))).map(EthLog.apply)


  override def ethGetFilterLogs(filterId: BigInt): F[EthLog] =
    services.fetch[List[EthLog.LogResult]](Request(method = "eth_getFilterLogs",params = List(filterId.asJson))).map(EthLog.apply)


  override def ethGetLogs(ethFilter: EthFilterRequest): F[EthLog] =
    val params = List(ethFilter.asJson.dropNullValues)
    val request = Request(method = "eth_getLogs", params)
    services.fetch[List[EthLog.LogResult]](request).map(EthLog.apply)


  override def ethGetWork: F[EthGetWork] =
    services.fetch[List[String]](Request(method = "eth_getWork")).map(EthGetWork.apply)


  override def ethSubmitWork(nonce: String, headerPowHash: String, mixDigest: String): F[EthSubmitWork] =
    val params = List(nonce, headerPowHash, mixDigest).map(_.asJson)
    val request = Request(method = "eth_submitWork", params)
    services.fetch[Boolean](request).map(EthSubmitWork.apply)


  override def ethSubmitHashrate(hashrate: String, clientId: String): F[EthSubmitHashrate] =
    val params = List(hashrate, clientId).map(_.asJson)
    val request = Request(method = "eth_submitHashrate", params)
    services.fetch[Boolean](request).map(EthSubmitHashrate.apply)


  override def dbPutString(databaseName: String, keyName: String, stringToStore: String): F[DbPutString] =
    val params = List(databaseName, keyName , stringToStore).map(_.asJson)
    val request = Request(method = "db_putString", params)
    services.fetch[Boolean](request).map(DbPutString.apply)


  override def dbGetString(databaseName: String, keyName: String): F[DbGetString] =
    val params = List(databaseName, keyName).map(_.asJson)
    val request = Request(method = "db_getString", params)
    services.fetch[String](request).map(DbGetString.apply)


  override def dbPutHex(databaseName: String, keyName: String, dataToStore: String): F[DbPutHex] =
    val params = List(databaseName, keyName, dataToStore).map(_.asJson)
    val request = Request(method = "db_putHex", params)
    services.fetch[Boolean](request).map(DbPutHex.apply)


  override def dbGetHex(databaseName: String, keyName: String): F[DbGetHex] =
    val params = List(databaseName, keyName).map(_.asJson)
    val request = Request(method = "db_getHex", params)
    services.fetch[String](request).map(DbGetHex.apply)


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
    services.fetch[Map[String, String]](Request(method = "txpool_status")).map(TxPoolStatus.apply)