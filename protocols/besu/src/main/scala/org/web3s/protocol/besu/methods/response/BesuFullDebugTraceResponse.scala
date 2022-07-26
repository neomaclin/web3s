package org.web3s.protocol.besu.methods.response

import org.web3s.protocol.core.Response

opaque type BesuFullDebugTraceResponse = Response[BesuFullDebugTraceResponse.FullDebugTraceInfo]

object BesuFullDebugTraceResponse:
  extension (x: BesuFullDebugTraceResponse)
    def fullDebugTraceInfo: BesuFullDebugTraceResponse.FullDebugTraceInfo = x.result

  final case class StructLogs(pc: Int,
                              op: String,
                              gas: Int,
                              gasCost: Int,
                              depth: Int,
                              stack: List[String],
                              memory: List[String],
                              storage: Map[Long, String])

  final case class FullDebugTraceInfo(gas: Int,
                                      failed: Boolean,
                                      returnValue: String,
                                      structLogs: List[StructLogs])

  def apply(responses: Response[FullDebugTraceInfo]): BesuFullDebugTraceResponse = responses


