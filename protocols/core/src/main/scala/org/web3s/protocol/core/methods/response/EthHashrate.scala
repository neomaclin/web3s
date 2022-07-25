package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.protocol.core.Response.EthBigInt
import org.web3s.utils.Numeric

opaque type EthHashrate = Response[EthBigInt]

object EthHashrate:
  def apply(response: Response[EthBigInt]): EthHashrate = response

extension (x: EthHashrate)
  def hashrate: BigInt = x.result
