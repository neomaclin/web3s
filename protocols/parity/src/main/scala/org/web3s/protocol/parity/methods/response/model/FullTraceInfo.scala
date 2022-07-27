package org.web3s.protocol.parity.methods.response.model

final case class FullTraceInfo(
                                output: String,
                                stateDiff: Map[String, StateDiff], 
                                trace: List[Trace], 
                                vmTrace: VMTrace
                              )
