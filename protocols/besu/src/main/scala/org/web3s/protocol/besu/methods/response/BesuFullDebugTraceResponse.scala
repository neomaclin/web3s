package org.web3s.protocol.besu.methods.response

import org.web3s.protocol.core.Response


opaque type BesuFullDebugTraceResponse = Response[BesuFullDebugTraceResponse.FullDebugTraceInfo]

object BesuFullDebugTraceResponse:
  final case class StructLogs(pc: Int,
                              op: String,
                              gas: Int,
                              gasCost: Int,
                              depth: Int,
                              stack: List[String],
                              memory: List[String],
                              storage: Map[BigInt, String])

  final case class FullDebugTraceInfo(gas: Int,
                                      failed: Boolean,
                                      returnValue: String,
                                      structLogs: List[StructLogs]
                                     )

  def apply(response: Response[FullDebugTraceInfo]): BesuFullDebugTraceResponse = response

extension (x: BesuFullDebugTraceResponse)
  def accounts: BesuFullDebugTraceResponse.FullDebugTraceInfo = x.result

