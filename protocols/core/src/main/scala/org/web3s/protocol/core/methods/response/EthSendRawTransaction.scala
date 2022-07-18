package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type EthSendRawTransaction = Response[String]

object EthSendRawTransaction:
  def apply(response: Response[String]): EthSendRawTransaction = response

extension (x: EthSendRawTransaction)
  def transactionHash: String = x.result
