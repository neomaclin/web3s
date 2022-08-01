package org.web3s.protocol.parity.methods.response

import io.circe.Decoder
import io.circe.generic.semiauto
import org.web3s.protocol.parity.methods.response.model.{StateDiff, Trace, VMTrace}

import StateDiff._
import Trace._

package model:

  import io.circe.Decoder
  import io.circe.syntax._

  import io.circe.generic.semiauto

  given Decoder[Mem] = semiauto.deriveDecoder
  given Decoder[Store] = semiauto.deriveDecoder
  given Decoder[Ex] = semiauto.deriveDecoder
  given Decoder[VMOperation] = semiauto.deriveDecoder
  given Decoder[VMTrace] = semiauto.deriveDecoder
  given Decoder[FullTraceInfo] = semiauto.deriveDecoder
  final case class VMTrace(code: String, ops: List[VMOperation])
  final case class Mem(data: String, off: Long)

  final case class Store(key: String, `val`: String)

  final case class Ex(mem: Mem, push: List[String], store: Store, used: BigInt)
  final case class VMOperation(sub: VMTrace, cost: BigInt, ex: Ex, pc: BigInt)
  final case class FullTraceInfo(output: String,
                                  stateDiff: Map[String, StateDiff],
                                  trace: List[Trace],
                                  vmTrace: VMTrace)



