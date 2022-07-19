package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response

opaque type EthLog = Response[List[EthLog.LogResult]]

object EthLog:
  type LogResult = EthLog.Log

  def apply(responses: Response[List[EthLog.LogResult]]): EthLog = responses

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

extension (x: EthLog)
  def logs: List[EthLog.LogResult] = x.result
