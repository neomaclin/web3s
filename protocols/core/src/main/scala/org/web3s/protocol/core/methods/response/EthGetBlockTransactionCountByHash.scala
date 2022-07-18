package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.utils.Numeric

opaque type EthGetBlockTransactionCountByHash = Response[String]

object EthGetBlockTransactionCountByHash:
  def apply(response: Response[String]): EthGetBlockTransactionCountByHash = response

extension (x: EthGetBlockTransactionCountByHash)
  def transactionCount: BigInt = Numeric.decodeQuantity(x.result)
