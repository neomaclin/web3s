package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.protocol.core.Response.EthBigInt
import org.web3s.utils.Numeric

opaque type EthBlockNumber = Response[EthBigInt]

object EthBlockNumber:
  def apply(response: Response[EthBigInt]): EthBlockNumber = response

extension (x: EthBlockNumber)
  def blockNumber: BigInt = x.result
