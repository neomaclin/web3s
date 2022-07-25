package org.web3s.protocol.admin.methods.response

import org.web3s.protocol.core.Response
import org.web3s.protocol.core.methods.response.EthTransaction
import org.web3s.protocol.core.methods.response.EthTransaction.Transaction


opaque type TxPoolContent = Response[TxPoolContent.TxPoolContentResult]

object TxPoolContent:
  import io.circe.Decoder
  import io.circe.generic.semiauto._
  import EthTransaction._
 // given Decoder[BigInt] = Decoder.decodeBigInt

  given Decoder[TxPoolContentResult] = deriveDecoder[TxPoolContentResult]

  final case class TxPoolContentResult(pending: Map[String, Map[Long, Transaction]],
                                       queued: Map[String, Map[Long, Transaction]])

  def apply(response: Response[TxPoolContentResult]): TxPoolContent = response


extension (x: TxPoolContent)
  def pendingTransactions: List[Transaction] = x.result.pending.values.flatMap(_.values).toList
  def queuedTransactions: List[Transaction] = x.result.queued.values.flatMap(_.values).toList
