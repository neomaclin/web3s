package org.web3s.protocol.besu.methods.response.privacy

class PrivGetPrivateTransaction {

}

opaque type PrivGetPrivateTransaction = Response[PrivGetPrivateTransaction.PrivateTransaction]

object PrivGetPrivateTransaction:
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

extension (x: PrivGetPrivateTransaction)
  def address: String = x.result

