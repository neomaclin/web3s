package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type EthGetCode = Response[String]

object EthGetCode:
  def apply(response: Response[String]): EthGetCode = response

extension (x: EthGetCode)
  def code: String = x.result
