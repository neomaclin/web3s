package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type EthGetTransactionReceipt = Response[Option[model.TransactionReceipt]]

object EthGetTransactionReceipt:
  def apply(response: Response[Option[model.TransactionReceipt]]): EthGetTransactionReceipt = response

extension (x: EthGetTransactionReceipt)
  def transactionReceipt: Option[model.TransactionReceipt] = x.result