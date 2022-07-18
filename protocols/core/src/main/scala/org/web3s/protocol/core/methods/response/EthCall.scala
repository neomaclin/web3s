package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type EthCall = Response[String]


object EthCall:
 // private val errorMethodId = "0x08c379a0"
  def apply(response: Response[String]): EthCall = response

extension (x: EthCall)
  def value: String = x.result
 // private def isErrorInResult: Boolean = x.result.startsWith(EthCall.errorMethodId)
