package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type EthAccounts = Response[Seq[String]]

object EthAccounts:
  def apply(responses: Response[Seq[String]]): EthAccounts = responses

extension (x: EthAccounts)
  def accounts: Seq[String] = x.result
