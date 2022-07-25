package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.protocol.core.Response.EthBigInt
import org.web3s.utils.Numeric

opaque type EthGetBlockTransactionCountByNumber = Response[EthBigInt]

object EthGetBlockTransactionCountByNumber:
  def apply(response: Response[EthBigInt]): EthGetBlockTransactionCountByNumber = response

