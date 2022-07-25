package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.protocol.core.Response.EthBigInt
import org.web3s.utils.Numeric

opaque type EthGetUncleCountByBlockNumber = Response[EthBigInt]

object EthGetUncleCountByBlockNumber:
  def apply(response: Response[EthBigInt]): EthGetUncleCountByBlockNumber = response
