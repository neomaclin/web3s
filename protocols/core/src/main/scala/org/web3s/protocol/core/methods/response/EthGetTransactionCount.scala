package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.utils.Numeric

opaque type EthGetTransactionCount = Response[String]

object EthGetTransactionCount:
  def apply(response: Response[String]): EthGetTransactionCount = response

extension (x: EthGetTransactionCount)
  def transactionCount: BigInt = Numeric.decodeQuantity(x.result)
