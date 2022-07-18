package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.utils.Numeric

opaque type EthMaxPriorityFeePerGas = Response[String]

object EthMaxPriorityFeePerGas:
  def apply(response: Response[String]): EthMaxPriorityFeePerGas = response

extension (x: EthMaxPriorityFeePerGas)
  def maxPriorityFeePerGas: BigInt = Numeric.decodeQuantity(x.result)
