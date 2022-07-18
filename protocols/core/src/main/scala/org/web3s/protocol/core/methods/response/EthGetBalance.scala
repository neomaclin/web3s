package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.utils.Numeric

opaque type EthGetBalance = Response[String]

object EthGetBalance:
  def apply(response: Response[String]): EthGetBalance = response

extension (x: EthGetBalance)
  def balance: BigInt = Numeric.decodeQuantity(x.result)
