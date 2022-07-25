package org.web3s.protocol.besu.methods.response.privacy

import org.web3s.protocol.core.Response

import org.web3s.protocol.core.methods.response.EthLog.Log

opaque type PrivGetTransactionReceipt = Response[PrivGetTransactionReceipt.PrivateTransactionReceipt]

object PrivGetTransactionReceipt:
  final case class PrivateTransactionReceipt(contractAddress: String,
                                             from: String,
                                             to: String,
                                             output: String,
                                             logs: List[Log],
                                             commitmentHash: String,
                                             transactionHash: String,
                                             privateFrom: String,
                                             privateFor: List[String],
                                             privacyGroupId: String,
                                             status: String,
                                             revertReason: String)

  def apply(response: Response[PrivateTransactionReceipt]): PrivGetTransactionReceipt = response

extension (x: PrivGetTransactionReceipt)
  def transactionReceipt: PrivGetTransactionReceipt.PrivateTransactionReceipt = x.result

