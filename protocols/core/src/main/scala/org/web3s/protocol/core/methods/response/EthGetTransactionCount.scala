package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.utils.EthBigInt

opaque type EthGetTransactionCount = Response[EthBigInt]

object EthGetTransactionCount:
  def apply(response: Response[EthBigInt]): EthGetTransactionCount = response

extension (x: EthGetTransactionCount)
  def transactionCount: BigInt = x.result.value
