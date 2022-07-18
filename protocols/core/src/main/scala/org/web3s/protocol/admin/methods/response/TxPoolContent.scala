package org.web3s.protocol.admin.methods.response

import org.web3s.protocol.core.Response
import org.web3s.protocol.core.methods.response.EthTransaction.Transaction


opaque type TxPoolContent = Response[TxPoolContent.TxPoolContentResult]

object TxPoolContent:
  final case class TxPoolContentResult(pending: Map[String, Map[BigInt, Transaction]],
                                       queued: Map[String, Map[BigInt, Transaction]])

  def apply(response: Response[TxPoolContentResult]): TxPoolContent = response


extension (x: TxPoolContent)
  def pendingTransactions: List[Transaction] = x.result.pending.values.flatMap(_.values).toList
  def queuedTransactions: List[Transaction] = x.result.queued.values.flatMap(_.values).toList
