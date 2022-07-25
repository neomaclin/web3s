package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.utils.EthBigInt
import org.web3s.utils.Numeric

opaque type EthMaxPriorityFeePerGas = Response[EthBigInt]

object EthMaxPriorityFeePerGas:
  extension (x: EthMaxPriorityFeePerGas)
    def maxPriorityFeePerGas: BigInt = x.result.value
    
  def apply(response: Response[EthBigInt]): EthMaxPriorityFeePerGas = response

