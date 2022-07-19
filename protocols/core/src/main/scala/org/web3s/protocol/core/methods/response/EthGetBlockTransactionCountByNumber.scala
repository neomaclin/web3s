package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.utils.Numeric

opaque type EthGetBlockTransactionCountByNumber = Response[String]

object EthGetBlockTransactionCountByNumber:
  def apply(response: Response[String]): EthGetBlockTransactionCountByNumber = response

