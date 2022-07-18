package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.utils.Numeric

opaque type EthChainId = Response[String]


object EthChainId:
  def apply(response: Response[String]): EthChainId = response

extension (x: EthChainId)
  def chainId: BigInt = Numeric.decodeQuantity(x.result)
