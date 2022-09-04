package org.web3s.protocol.parity.methods.response

import io.circe.Json
import org.web3s.protocol.core.Response

opaque type ParityAllAccountsInfo = Response[Map[String, ParityAllAccountsInfo.AccountsInfo]]

object ParityAllAccountsInfo:

  final case class AccountsInfo(meta: Map[String, Json], name: String, uuid: String)

  def apply(response: Response[Map[String, AccountsInfo]]): ParityAllAccountsInfo = response

extension (x: ParityAllAccountsInfo)
  def accountsInfo: Map[String, ParityAllAccountsInfo.AccountsInfo] = x.result