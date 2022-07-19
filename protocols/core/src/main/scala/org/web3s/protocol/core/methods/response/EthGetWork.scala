package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type EthGetWork = Response[List[String]]

object EthGetWork:
  def apply(response: Response[List[String]]): EthGetWork = response

extension (x: EthGetWork)
  def currentBlockHeaderPowHash: String  = x.result.headOption.getOrElse("")
  def seedHashForDag: String = x.result(1)
  def boundaryCondition: String =  x.result(2)


