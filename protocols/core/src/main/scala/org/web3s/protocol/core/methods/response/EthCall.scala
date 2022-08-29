package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type EthCall = Response[String]


object EthCall:
  val errorMethodId = "0x08c379a0"
  def apply(response: Response[String]): EthCall = response

extension (x: EthCall)
  private def isErrorInResult: Boolean = x.result.startsWith(EthCall.errorMethodId)
  def value: String = x.result
  def isReverted: Boolean =
    if x.error.exists(_.code == 3) && x.error.flatMap(_.data).nonEmpty
      then false
      else x.error.nonEmpty || isErrorInResult
  def revertReason: String =
    if isErrorInResult then x.result
    else if x.error.nonEmpty then x.error.get.message
    else ""
