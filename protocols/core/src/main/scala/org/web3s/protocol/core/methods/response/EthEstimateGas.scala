package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.utils.EthBigInt
import org.web3s.utils.Numeric

opaque type EthEstimateGas = Response[EthBigInt]

object EthEstimateGas:

  def apply(response: Response[EthBigInt]): EthEstimateGas = response

extension (x: EthEstimateGas)
  def amountUsed: BigInt = x.result.value