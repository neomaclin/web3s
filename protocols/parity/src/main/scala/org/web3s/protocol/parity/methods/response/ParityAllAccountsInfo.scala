package org.web3s.protocol.parity.methods.response

import io.circe.Json
import org.web3s.protocol.core.Response

opaque type ParityAllAccountsInfo = Response[Map[String, ParityAllAccountsInfo.AccountsInfo]]

object ParityAllAccountsInfo:

  import io.circe.Decoder
  import io.circe.syntax._
  import io.circe.generic.semiauto._

  given Decoder[AccountsInfo] = deriveDecoder[AccountsInfo]

  final case class AccountsInfo(meta: Map[String, Json], name: String, uuid: String)

  extension (x: ParityAllAccountsInfo)
    def accountsInfo: Map[String, AccountsInfo] = x.result

  def apply(response: Response[Map[String, AccountsInfo]]): ParityAllAccountsInfo = response

