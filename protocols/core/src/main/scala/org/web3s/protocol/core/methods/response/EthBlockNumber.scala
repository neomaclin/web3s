package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.utils.Numeric

opaque type EthBlockNumber = Response[String]

object EthBlockNumber:
  def apply(response: Response[String]): EthBlockNumber = response

extension (x: EthBlockNumber)
  def blockNumber: BigInt = Numeric.decodeQuantity(x.result)
