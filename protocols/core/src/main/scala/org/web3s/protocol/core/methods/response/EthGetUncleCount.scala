package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.utils.EthBigInt
import org.web3s.utils.Numeric

opaque type EthGetUncleCount = Response[EthBigInt]

object EthGetUncleCount:

  def apply(response: Response[EthBigInt]): EthGetUncleCount = response

extension (x: EthGetUncleCount)
  def uncleCount: BigInt = x.result.value
