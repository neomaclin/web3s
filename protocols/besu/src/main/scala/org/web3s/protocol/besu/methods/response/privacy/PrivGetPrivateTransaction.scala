package org.web3s.protocol.besu.methods.response.privacy

import org.web3s.protocol.core.Response
import org.web3s.protocol.eea.util.*

opaque type PrivGetPrivateTransaction = Response[PrivGetPrivateTransaction.PrivateTransaction]

object PrivGetPrivateTransaction:
//
//  extension (x: PrivGetPrivateTransaction)
//    def address: String = x.result
//
  final case class PrivateTransaction(
                                       hash: String,
                                       nonce: String,
                                       from: String,
                                       to: String,
                                       value: String,
                                       gas: String,
                                       gasPrice: String,
                                       input: String,
                                       r: String,
                                       s: String,
                                       v: String,
                                       privateFrom: Base64String,
                                       restriction: Restriction
                                     )

  def apply(response: Response[PrivateTransaction]): PrivGetPrivateTransaction = response


