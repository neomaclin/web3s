package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.utils.Numeric

opaque type EthGasPrice = Response[String]

object EthGasPrice:
  def apply(response: Response[String]): EthGasPrice = response

extension (x: EthGasPrice)
  def gasPrice: BigInt = Numeric.decodeQuantity(x.result)
