package org.web3s.protocol.geth.methods.response

import org.web3s.protocol.core.Response

opaque type PersonalEcRecover = Response[String]

object PersonalEcRecover:
  def apply(response: Response[String]): PersonalEcRecover = response

extension (x: PersonalEcRecover)
  def recoverAccountId: String = x.result
