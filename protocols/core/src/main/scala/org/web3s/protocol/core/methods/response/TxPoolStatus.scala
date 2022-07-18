package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type TxPoolStatus = Response[Map[String, String]]

object TxPoolStatus:
  def apply(response: Response[Map[String, String]]): TxPoolStatus = response

extension (x: TxPoolStatus)
  def pending: Int = x.result.getOrElse("pending", "0").toInt
  def queued: Int = x.result.getOrElse("pending", "0").toInt