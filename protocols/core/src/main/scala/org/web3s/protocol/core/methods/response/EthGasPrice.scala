package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.utils.EthBigInt
import org.web3s.utils.Numeric

opaque type EthGasPrice = Response[EthBigInt]

object EthGasPrice:
  extension (x: EthGasPrice)
    def gasPrice: BigInt = x.result.value

  def apply(response: Response[EthBigInt]): EthGasPrice = response

