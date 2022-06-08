package org.web3s.protocol.core.methods

import org.web3s.protocol.core.Response
import org.web3s.utils.Numeric

package response {

  opaque type BooleanResponse = Response[Boolean]
  opaque type DbGetHex = Response[String]
  opaque type DbGetString = Response[String]
  opaque type DbPutHex = Response[String]
  opaque type DbPutString = Response[String]
  opaque type EthAccounts = Response[Seq[String]]
  opaque type EthBlockNumber = Response[String]
  opaque type EthCall = Response[String]
  opaque type EthChainId = Response[String]
  opaque type EthCoinbase = Response[String]
  opaque type EthCompileLLL = Response[String]
  opaque type EthEstimateGas = Response[String]
  opaque type EthCompileSerpent = Response[String]
  opaque type EthFilter = Response[String]
  opaque type EthGasPrice = Response[String]
  opaque type EthGetBalance = Response[String]
  opaque type EthGetBlockTransactionCountByHash = Response[String]
  opaque type EthGetBlockTransactionCountByNumber = Response[String]
  opaque type EthGetCode = Response[String]
  opaque type EthGetStorageAt = Response[String]
  opaque type EthGetTransactionCount = Response[String]
  opaque type EthGetUncleCountByBlockHash = Response[String]
  opaque type EthGetUncleCountByBlockNumber = Response[String]
  opaque type EthHashrate = Response[String]
  opaque type EthMining = Response[Boolean]
  opaque type EthProtocolVersion = Response[String]
  opaque type EthSendRawTransaction = Response[String]
  opaque type EthSendTransaction = Response[String]
  opaque type EthSign = Response[String]
  opaque type EthSubmitHashrate = Response[Boolean]
  opaque type EthSubmitWork = Response[Boolean]
  opaque type EthSubscribe = Response[String]
  opaque type EthUninstallFilter = Response[Boolean]
  opaque type EthUnsubscribe = Response[Boolean]
  opaque type EthSign = Response[String]
  opaque type EthGetCompilers = Response[List[String]]
  opaque type EthGetWork = Response[List[String]]
  opaque type NetPeerCount = Response[String]
  opaque type NetVersion = Response[String]
  opaque type Web3ClientVersion = Response[String]
  opaque type Web3Sha3 = Response[String]

  extension (r: EthBlockNumber)
    def blockNumber: Long = Numeric.decodeQuantity(r.result)

}

