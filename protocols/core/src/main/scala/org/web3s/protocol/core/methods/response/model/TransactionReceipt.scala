package org.web3s.protocol.core.methods.response.model

import org.web3s.protocol.core.methods.response.EthLog

final case class TransactionReceipt(
                                     transactionHash: String,
                                     transactionIndex: String,
                                     blockHash: String,
                                     blockNumber: String,
                                     cumulativeGasUsed: String,
                                     gasUsed: String,
                                     contractAddress: String,
                                     root: String,
                                     status: String,
                                     from: String,
                                     to: String,
                                     logs: List[EthLog.Log],
                                     logsBloom: String,
                                     revertReason: String,
                                     `type`: String,
                                     effectiveGasPrice: String
                                   )