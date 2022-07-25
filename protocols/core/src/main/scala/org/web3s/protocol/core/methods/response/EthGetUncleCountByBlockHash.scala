package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.utils.EthBigInt
import org.web3s.utils.Numeric

opaque type EthGetUncleCountByBlockHash = Response[EthBigInt]

object EthGetUncleCountByBlockHash:

  extension (x: EthGetUncleCountByBlockHash)
    def uncleCount: BigInt = x.result.value

  def apply(response: Response[EthBigInt]): EthGetUncleCountByBlockHash = response
