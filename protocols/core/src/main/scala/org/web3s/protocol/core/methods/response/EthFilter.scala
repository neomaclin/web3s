package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.utils.Numeric


opaque type EthFilter = Response[String]

object EthFilter:
  def apply(response: Response[String]): EthFilter = response

extension (x: EthFilter)
  def filterId: BigInt = Numeric.decodeQuantity(x.result)
