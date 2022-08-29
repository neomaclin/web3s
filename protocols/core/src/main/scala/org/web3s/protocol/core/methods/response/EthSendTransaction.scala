package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type EthSendTransaction = Response[String]

object EthSendTransaction:
  def apply(response: Response[String]): EthSendTransaction = response


extension (x: EthSendTransaction)
  def transactionHash: String = x.result
