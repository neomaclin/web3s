package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type EthSign = Response[String]

object EthSign:
  def apply(response: Response[String]): EthSign = response

extension (x: EthSign)
  def signature: String = x.result
