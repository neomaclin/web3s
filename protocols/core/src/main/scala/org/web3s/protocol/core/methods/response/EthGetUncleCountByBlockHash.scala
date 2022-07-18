package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.utils.Numeric

opaque type EthGetUncleCountByBlockHash = Response[String]

object EthGetUncleCountByBlockHash:
  def apply(response: Response[String]): EthGetUncleCountByBlockHash = response

extension (x: EthGetUncleCountByBlockHash)
  def uncleCount: BigInt = Numeric.decodeQuantity(x.result)