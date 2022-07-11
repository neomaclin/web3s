package org.web3s.protocol.core.methods.request

final case class ShhPost(
                          from: String,
                          to: String,
                          topics: List[String],
                          payload: String,
                          priority: BigInt,
                          ttl: BigInt
                        )


