package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.utils.Numeric

opaque type EthHashrate = Response[String]

object EthHashrate:
  def apply(response: Response[String]): EthHashrate = response

extension (x: EthHashrate)
  def hashrate: BigInt = Numeric.decodeQuantity(x.result)
