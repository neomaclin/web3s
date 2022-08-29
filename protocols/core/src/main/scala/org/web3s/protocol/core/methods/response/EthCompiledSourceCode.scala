package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type EthCompiledSourceCode = Response[String]

object EthCompiledSourceCode:
  def apply(response: Response[String]): EthCompiledSourceCode = response

extension (x: EthCompiledSourceCode)
  def compiledSourceCode: String = x.result
