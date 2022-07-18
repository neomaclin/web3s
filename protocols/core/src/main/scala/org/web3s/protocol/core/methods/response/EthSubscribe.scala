package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type EthSubscribe = Response[String]

object EthSubscribe:
  def apply(response: Response[String]): EthSubscribe = response

extension (x: EthSubscribe)
  def subscriptionId: String = x.result
