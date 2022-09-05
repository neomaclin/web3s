package org.web3s.protocol.besu.methods.response.privacy

import org.web3s.protocol.besu.methods.response.model.PrivateTransaction
import org.web3s.protocol.core.Response


opaque type PrivGetPrivateTransaction = Response[Option[PrivateTransaction]]

object PrivGetPrivateTransaction:
  def apply(response: Response[Option[PrivateTransaction]]): PrivGetPrivateTransaction = response

extension (x: PrivGetPrivateTransaction)
  def privateTransaction: Option[PrivateTransaction] = x.result
