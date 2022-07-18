package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response


opaque type EthLog = Response[List[EthLog.LogResult]]

object EthLog:
  type LogResult = EthLog.Log | String

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

  def apply(response: Response[List[LogResult]]): EthGetWork = response

extension (x: EthLog)
  def logs: List[EthLog.LogResult] = x.result
