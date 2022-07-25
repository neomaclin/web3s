package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.protocol.core.Response.EthBigInt
import org.web3s.utils.Numeric

opaque type EthMaxPriorityFeePerGas = Response[EthBigInt]

object EthMaxPriorityFeePerGas:
  def apply(response: Response[EthBigInt]): EthMaxPriorityFeePerGas = response

extension (x: EthMaxPriorityFeePerGas)
  def maxPriorityFeePerGas: BigInt = x.result
