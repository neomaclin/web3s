package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.protocol.core.Response.EthBigInt
import org.web3s.utils.Numeric

opaque type NetPeerCount = Response[EthBigInt]

object NetPeerCount:
  def apply(response: Response[EthBigInt]): NetPeerCount = response

extension (x: NetPeerCount)
  def quantity: BigInt = x.result
