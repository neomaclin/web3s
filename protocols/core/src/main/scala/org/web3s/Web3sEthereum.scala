package org.web3s

import cats.effect.Async
import cats.syntax.functor.*
import org.web3s.protocol.admin.methods.response.TxPoolContent
import org.web3s.protocol.core.*
import org.web3s.protocol.core.methods.request.Transaction
import org.web3s.protocol.core.methods.response.*
import org.web3s.protocol.core.methods.response.admin.*
import org.web3s.services.Web3sService


class Web3sEthereum[F[_] : Async](services: Web3sService[F]) extends Ethereum[F]:
  import io.circe._
  import io.circe.syntax._
  import io.circe.generic.auto._
  override def web3ClientVersion: F[Web3ClientVersion] =
    services.fetch[String](Request(method = "web3_clientVersion")).map(Web3ClientVersion.apply)

  override def web3Sha3(data: String): F[Web3Sha3] =
    services.fetch[String](Request(method = "web3_sha3", params = List(data.asJson))).map(Web3Sha3.apply)

  override def netVersion: F[NetVersion] =
    services.fetch[String](Request(method = "net_version")).map(NetVersion.apply)

  override def netListening: F[NetListening] =
    services.fetch[Boolean](Request(method = "net_listening")).map(NetListening.apply)

  override def netPeerCount: F[NetPeerCount] =
    services.fetch[String](Request(method = "net_peerCount")).map(NetPeerCount.apply)

  override def adminNodeInfo: F[AdminNodeInfo] =
    services.fetch[AdminNodeInfo.NodeInfo](Request(method = "admin_nodeInfo")).map(AdminNodeInfo.apply)

  override def adminPeers: F[AdminPeers] =
    services.fetch[List[AdminPeers.Peer]](Request(method = "admin_peers")).map(AdminPeers.apply)

  override def adminAddPeer(url: String): F[BooleanResponse] =
    services.fetch[Boolean](Request(method = "admin_addPeer", params = List(url.asJson))).map(BooleanResponse.apply)

  override def adminRemovePeer(url: String): F[BooleanResponse] =
    services.fetch[Boolean](Request(method = "admin_removePeer", params = List(url.asJson))).map(BooleanResponse.apply)

  override def adminDataDir: F[AdminDataDir] =
    services.fetch[String](Request(method = "admin_datadir")).map(AdminDataDir.apply)

  override def ethProtocolVersion: F[EthProtocolVersion] =
    services.fetch[String](Request(method = "eth_protocolVersion")).map(EthProtocolVersion.apply)

  override def ethChainId: F[EthChainId] =
    services.fetch[String](Request(method = "eth_chainId")).map(EthChainId.apply)

  override def ethCoinbase: F[EthCoinbase] =
    services.fetch[String](Request(method = "eth_coinbase")).map(EthCoinbase.apply)

  override def ethSyncing: F[EthSyncing] =
    services.fetch[EthSyncing.Result](Request(method = "eth_syncing")).map(EthSyncing.apply)

  override def ethMining: F[EthMining] =
    services.fetch[Boolean](Request(method = "eth_mining")).map(EthMining.apply)

  override def ethHashrate: F[EthHashrate] =
    services.fetch[String](Request(method = "eth_hashrate")).map(EthHashrate.apply)

  override def ethGasPrice: F[EthGasPrice] =
    services.fetch[String](Request(method = "eth_gasPrice")).map(EthGasPrice.apply)

  override def ethMaxPriorityFeePerGas: F[EthMaxPriorityFeePerGas] =
    services.fetch[String](Request(method = "eth_maxPriorityFeePerGas")).map(EthMaxPriorityFeePerGas.apply)

  override def ethFeeHistory(blockCount: Int, newestBlock: DefaultBlockParameter, rewardPercentiles: List[Double]): F[EthFeeHistory] =
    services.fetch[EthFeeHistory.FeeHistory](Request(method = "eth_feeHistory")).map(EthFeeHistory.apply)

  override def ethAccounts: F[EthAccounts] =
    services.fetch[Seq[String]](Request(method = "eth_accounts")).map(EthAccounts.apply)

  override def ethBlockNumber: F[EthBlockNumber] =
    services.fetch[String](Request(method = "eth_blockNumber")).map(EthBlockNumber.apply)

  override def ethGetBalance(address: String, defaultBlockParameter: DefaultBlockParameter): F[EthGetBalance] =
    services.fetch[String](Request(method = "eth_getBalance")).map(EthGetBalance.apply)

  override def ethGetStorageAt(address: String, position: BigInt, defaultBlockParameter: DefaultBlockParameter): F[EthGetStorageAt] =
    services.fetch[String](Request(method = "eth_getStorageAt")).map(EthGetStorageAt.apply)

  override def ethGetTransactionCount(address: String, defaultBlockParameter: DefaultBlockParameter): F[EthGetTransactionCount] =
    services.fetch[String](Request(method = "eth_getTransactionCount")).map(EthGetTransactionCount.apply)


  override def ethGetBlockTransactionCountByHash(blockHash: String): F[EthGetBlockTransactionCountByHash] =
    services.fetch[String](Request(method = "eth_getBlockTransactionCountByHash")).map(EthGetBlockTransactionCountByHash.apply)


  override def ethGetBlockTransactionCountByNumber(defaultBlockParameter: DefaultBlockParameter): F[EthGetBlockTransactionCountByNumber] =
    services.fetch[String](Request(method = "eth_getBlockTransactionCountByNumber")).map(EthGetBlockTransactionCountByNumber.apply)


  override def ethGetUncleCountByBlockHash(blockHash: String): F[EthGetUncleCountByBlockHash] =
    services.fetch[String](Request(method = "eth_getUncleCountByBlockHash")).map(EthGetUncleCountByBlockHash.apply)


  override def ethGetUncleCountByBlockNumber(defaultBlockParameter: DefaultBlockParameter): F[EthGetUncleCountByBlockNumber] =
    services.fetch[String](Request(method = "eth_getUncleCountByBlockNumber")).map(EthGetUncleCountByBlockNumber.apply)


  override def ethGetCode(address: String, defaultBlockParameter: DefaultBlockParameter): F[EthGetCode] =
    services.fetch[String](Request(method = "eth_getCode")).map(EthGetCode.apply)


  override def ethSign(address: String, sha3HashOfDataToSign: String): F[EthSign] =
    services.fetch[String](Request(method = "eth_sign")).map(EthSign.apply)


  override def ethSendTransaction(transaction: Transaction): F[EthSendTransaction] =
    services.fetch[String](Request(method = "eth_sendTransaction")).map(EthSendTransaction.apply)


  override def ethSendRawTransaction(signedTransactionData: String): F[EthSendTransaction] =
    services.fetch[String](Request(method = "eth_sendRawTransaction")).map(EthSendTransaction.apply)


  override def ethCall(transaction: Transaction, defaultBlockParameter: DefaultBlockParameter): F[EthCall] =
    services.fetch[String](Request(method = "eth_call")).map(EthCall.apply)


  override def ethEstimateGas(transaction: Transaction): F[EthEstimateGas] =
    services.fetch[String](Request(method = "eth_estimateGas")).map(EthEstimateGas.apply)


  override def ethGetBlockByHash(blockHash: String, returnFullTransactionObjects: Boolean): F[EthBlock] =
    import EthBlock._
    services.fetch[EthBlock.Block](Request(method = "eth_getBlockByHash")).map(EthBlock.apply)


  override def ethGetBlockByNumber(defaultBlockParameter: DefaultBlockParameter, returnFullTransactionObjects: Boolean): F[EthBlock] =
    import EthBlock._
    services.fetch[EthBlock.Block](Request(method = "eth_getBlockByNumber")).map(EthBlock.apply)


  override def ethGetTransactionByHash(transactionHash: String): F[EthTransaction] =
    import EthTransaction._
    services.fetch[Option[EthTransaction.Transaction]](Request(method = "eth_getTransactionByHash")).map(EthTransaction.apply)


  override def ethGetTransactionByBlockHashAndIndex(blockHash: String, transactionIndex: BigInt): F[EthTransaction] =
    import EthTransaction._
    services.fetch[Option[EthTransaction.Transaction]](Request(method = "eth_gasPrice")).map(EthTransaction.apply)


  override def ethGetTransactionByBlockNumberAndIndex(defaultBlockParameter: DefaultBlockParameter, transactionIndex: BigInt): F[EthTransaction] =
    import EthTransaction._
    services.fetch[Option[EthTransaction.Transaction]](Request(method = "eth_getTransactionByBlockHashAndIndex")).map(EthTransaction.apply)


  override def ethGetTransactionReceipt(transactionHash: String): F[EthGetTransactionReceipt] =
    services.fetch[Option[EthGetTransactionReceipt.TransactionReceipt]](Request(method = "eth_getTransactionReceipt")).map(EthGetTransactionReceipt.apply)


  override def ethGetUncleByBlockHashAndIndex(blockHash: String, transactionIndex: BigInt): F[EthBlock] =
    services.fetch[EthBlock.Block](Request(method = "eth_getUncleByBlockHashAndIndex")).map(EthBlock.apply)


  override def ethGetUncleByBlockNumberAndIndex(defaultBlockParameter: DefaultBlockParameter, transactionIndex: BigInt): F[EthBlock] =
    services.fetch[EthBlock.Block](Request(method = "eth_getUncleByBlockNumberAndIndex")).map(EthBlock.apply)


  override def ethGetCompilers: F[EthGetCompilers] =
    services.fetch[List[String]](Request(method = "eth_getCompilers")).map(EthGetCompilers.apply)


  override def ethCompileLLL(sourceCode: String): F[EthCompileLLL] =
    services.fetch[String](Request(method = "eth_compileLLL")).map(EthCompileLLL.apply)


  override def ethCompileSolidity(sourceCode: String): F[EthCompileSolidity] =
    import EthCompileSolidity._

    services.fetch[Map[String, EthCompileSolidity.Code]](Request(method = "eth_compileSolidity")).map(EthCompileSolidity.apply)


  override def ethCompileSerpent(sourceCode: String): F[EthCompileSerpent] =
    services.fetch[String](Request(method = "eth_compileSerpent")).map(EthCompileSerpent.apply)


  override def ethNewFilter(ethFilter: EthFilter): F[EthFilter] =
    services.fetch[String](Request(method = "eth_newFilter")).map(EthFilter.apply)


  override def ethNewBlockFilter: F[EthFilter] =
    services.fetch[String](Request(method = "eth_newBlockFilter")).map(EthFilter.apply)


  override def ethNewPendingTransactionFilter: F[EthFilter] =
    services.fetch[String](Request(method = "eth_newPendingTransactionFilter")).map(EthFilter.apply)


  override def ethUninstallFilter(filterId: BigInt): F[EthUninstallFilter] =
    services.fetch[Boolean](Request(method = "eth_uninstallFilter")).map(EthUninstallFilter.apply)


  override def ethGetFilterChanges(filterId: BigInt): F[EthLog] =
    services.fetch[List[EthLog.LogResult]](Request(method = "eth_getFilterChanges")).map(EthLog.apply)


  override def ethGetFilterLogs(filterId: BigInt): F[EthLog] =
    services.fetch[List[EthLog.LogResult]](Request(method = "eth_getFilterLogs")).map(EthLog.apply)


  override def ethGetLogs(ethFilter: EthFilter): F[EthLog] =
    services.fetch[List[EthLog.LogResult]](Request(method = "eth_getLogs")).map(EthLog.apply)


  override def ethGetWork: F[EthGetWork] =
    services.fetch[List[String]](Request(method = "eth_getWork")).map(EthGetWork.apply)


  override def ethSubmitWork(nonce: String, headerPowHash: String, mixDigest: String): F[EthSubmitWork] =
    services.fetch[Boolean](Request(method = "eth_submitWork")).map(EthSubmitWork.apply)


  override def ethSubmitHashrate(hashrate: String, clientId: String): F[EthSubmitHashrate] =
    services.fetch[Boolean](Request(method = "eth_submitHashrate")).map(EthSubmitHashrate.apply)


  override def dbPutString(databaseName: String, keyName: String, stringToStore: String): F[DbPutString] =
    services.fetch[Boolean](Request(method = "db_putString")).map(DbPutString.apply)


  override def dbGetString(databaseName: String, keyName: String): F[DbGetString] =
    services.fetch[String](Request(method = "db_getString")).map(DbGetString.apply)


  override def dbPutHex(databaseName: String, keyName: String, dataToStore: String): F[DbPutHex] =
    services.fetch[Boolean](Request(method = "db_putHex")).map(DbPutHex.apply)


  override def dbGetHex(databaseName: String, keyName: String): F[DbGetHex] =
    services.fetch[String](Request(method = "db_getHex")).map(DbGetHex.apply)


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