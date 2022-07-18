package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response


opaque type EthCompileLLL = Response[String]

object EthCompileLLL:
  def apply(response: Response[String]): EthCompileLLL = response

extension (x: EthCompileLLL)
  def compiledSourceCode: BigInt = x.result
