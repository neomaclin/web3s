package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type EthGetCompilers = Response[List[String]]

object EthGetCompilers:
  def apply(response: Response[List[String]]): EthGetCompilers = response

extension (x: EthGetCompilers)
  def compilers: List[String] = x.result