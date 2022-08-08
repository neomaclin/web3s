package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.utils.EthBigInt
import org.web3s.utils.Numeric

opaque type EthGetBalance = Response[EthBigInt]

object EthGetBalance:

  def apply(response: Response[EthBigInt]): EthGetBalance = response

extension (x: EthGetBalance)
  def balance: BigInt = x.result.value