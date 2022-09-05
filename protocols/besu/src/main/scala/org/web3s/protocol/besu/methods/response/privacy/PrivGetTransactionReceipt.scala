package org.web3s.protocol.besu.methods.response.privacy

import org.web3s.protocol.besu.methods.response.model.PrivateTransactionReceipt
import org.web3s.protocol.core.Response

opaque type PrivGetTransactionReceipt = Response[PrivateTransactionReceipt]

object PrivGetTransactionReceipt:

  def apply(response: Response[PrivateTransactionReceipt]): PrivGetTransactionReceipt = response

extension (x: PrivGetTransactionReceipt)
  def privateTransactionReceipt: PrivateTransactionReceipt = x.result

