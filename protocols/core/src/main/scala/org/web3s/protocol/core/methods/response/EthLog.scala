package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response


opaque type EthLog = Response[List[EthLog.LogResult]]

object EthLog:
  type LogResult = model.Log
  def apply(responses: Response[List[EthLog.LogResult]]): EthLog = responses

extension (x: EthLog)
  def logs: List[EthLog.LogResult] = x.result
