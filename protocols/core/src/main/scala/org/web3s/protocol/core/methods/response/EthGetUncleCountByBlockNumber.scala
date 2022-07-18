package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.utils.Numeric

opaque type EthGetUncleCountByBlockNumber = Response[String]

object EthGetUncleCountByBlockNumber:
  def apply(response: Response[String]): EthGetUncleCountByBlockNumber = response

extension (x: EthGetUncleCountByBlockNumber)
  def uncleCount: BigInt = Numeric.decodeQuantity(x.result)