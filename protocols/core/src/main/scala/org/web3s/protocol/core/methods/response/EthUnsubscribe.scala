package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type EthUnsubscribe = Response[Boolean]

object EthUnsubscribe:
  def apply(response: Response[Boolean]): EthUnsubscribe = response

