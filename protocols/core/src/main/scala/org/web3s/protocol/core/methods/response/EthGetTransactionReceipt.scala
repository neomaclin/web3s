package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type EthGetTransactionReceipt = Response[Option[EthGetTransactionReceipt.TransactionReceipt]]

object EthGetTransactionReceipt:

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

  def apply(response: Response[Option[TransactionReceipt]]): EthGetTransactionReceipt = response

extension (x: EthGetTransactionReceipt)
  def transactionReceipt: Option[EthGetTransactionReceipt.TransactionReceipt] = x.result