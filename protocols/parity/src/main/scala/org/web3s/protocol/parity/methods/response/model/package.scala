package org.web3s.protocol.parity.methods.response

package model:
  final case class VMTrace(code: String, ops: List[VMOperation])
  final case class Mem(data: String, off: BigInt)
  final case class Store(key: String, `val`: String)
  final case class Ex(mem: Option[Mem], push: List[String], store: Option[Store], used: BigInt)
  final case class VMOperation(sub: Option[VMTrace], cost: BigInt, ex: Ex, pc: BigInt)
  final case class FullTraceInfo(output: String,
                                  stateDiff: Option[Map[String, StateDiff]],
                                  trace: Option[List[Trace]],
                                  vmTrace: Option[VMTrace])



