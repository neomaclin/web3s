package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.utils.Numeric

opaque type NetPeerCount = Response[String]

object NetPeerCount:
  def apply(response: Response[String]): NetPeerCount = response

extension (x: NetPeerCount)
  def quantity: BigInt = Numeric.decodeQuantity(x.result)
