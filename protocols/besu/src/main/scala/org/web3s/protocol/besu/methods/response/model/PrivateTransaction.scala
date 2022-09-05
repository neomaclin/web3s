package org.web3s.protocol.besu.methods.response.model

import org.web3s.protocol.eea.util.*

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
                                     privateFor: List[Base64String] | Base64String,
                                     restriction: Restriction
                                   )

