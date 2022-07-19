package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type EthCompileSerpent = Response[String]

object EthCompileSerpent:
  def apply(response: Response[String]): EthCompileSerpent = response
