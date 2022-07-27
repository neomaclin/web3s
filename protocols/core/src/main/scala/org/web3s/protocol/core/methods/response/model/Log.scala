package org.web3s.protocol.core.methods.response.model

final case class Log(
                      removed: Boolean,
                      logIndex: String,
                      transactionIndex: String,
                      transactionHash: String,
                      blockHash: String,
                      blockNumber: String,
                      address: String,
                      data: String,
                      `type`: String,
                      topics: List[String]
                    )