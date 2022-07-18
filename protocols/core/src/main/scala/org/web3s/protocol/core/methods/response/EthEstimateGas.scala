package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.utils.Numeric

opaque type EthEstimateGas = Response[String]

object EthEstimateGas:
  def apply(response: Response[String]): EthEstimateGas = response

extension (x: EthEstimateGas)
  def amountUsed: BigInt = Numeric.decodeQuantity(x.result)
