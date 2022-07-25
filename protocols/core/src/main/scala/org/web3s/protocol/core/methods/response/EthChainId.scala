package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.protocol.core.Response.EthBigInt
import org.web3s.utils.Numeric

opaque type EthChainId = Response[EthBigInt]


object EthChainId:
  def apply(response: Response[EthBigInt]): EthChainId = response

extension (x: EthChainId)
  def chainId: BigInt = x.result
