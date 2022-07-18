package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type EthCoinbase = Response[String]

object EthCoinbase:
  def apply(response: Response[String]): EthCoinbase = response

extension (x: EthCoinbase)
  def address: String = x.result
