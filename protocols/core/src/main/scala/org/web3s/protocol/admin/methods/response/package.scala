package org.web3s.protocol.admin.methods

import org.web3s.protocol.core.Response
import org.web3s.protocol.core.methods.response.Transaction

package response {
  final case class TxPoolContentResult(pending: Map[String, Map[BigInt, Transaction]],
                                       queued: Map[String, Map[BigInt, Transaction]])

  opaque type BooleanResponse = Response[Boolean]
  opaque type NewAccountIdentifier = Response[String]
  opaque type PersonalListAccounts = Response[List[String]]
  opaque type PersonalSign = Response[String]
  opaque type PersonalUnlockAccount = Response[Boolean]
  opaque type TxPoolContent = Response[TxPoolContentResult]
}
