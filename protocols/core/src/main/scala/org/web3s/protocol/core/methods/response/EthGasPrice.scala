package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.protocol.core.Response.EthBigInt
import org.web3s.utils.Numeric

opaque type EthGasPrice = Response[EthBigInt]

object EthGasPrice:
  def apply(response: Response[EthBigInt]): EthGasPrice = response

extension (x: EthGasPrice)
  def gasPrice: BigInt = x.result
