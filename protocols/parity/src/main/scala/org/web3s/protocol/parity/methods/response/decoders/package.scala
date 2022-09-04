package org.web3s.protocol.parity.methods.response

import io.circe.Decoder
import io.circe.syntax._
import io.circe.generic.semiauto._
import cats.syntax.functor._

import org.web3s.protocol.parity.methods.response.*
import org.web3s.protocol.parity.methods.response.model.*

package decoders:

  given Decoder[Trace.Result] = deriveDecoder[Trace.Result]
  given Decoder[Trace.Action] = List(
    deriveDecoder[Trace.Action.SuicideAction].widen,
    deriveDecoder[Trace.Action.CallAction].widen,
    deriveDecoder[Trace.Action.RewardAction].widen,
    deriveDecoder[Trace.Action.CreateAction].widen,
  ).reduce(_ or _)
  given Decoder[Trace] = deriveDecoder[Trace]

  private def map2ChangedState(input: Map[String, String]): Option[StateDiff.State.ChangedState] =
    for
      from <- input.get("from")
      to <- input.get("to")
    yield
      StateDiff.State.ChangedState(from, to)


  given Decoder[StateDiff.State] = List(
    Decoder.decodeString.emap(str => if str == "=" then Right(StateDiff.State.UnchangedState) else Left(str)).widen,
    Decoder.decodeMap[String, String].emap(map => map.get("+").map(StateDiff.State.AddedState.apply).toRight(map.mkString)).widen,
    Decoder.decodeMap[String, Map[String, String]].emap(map => map.get("*").flatMap(map2ChangedState).toRight(map.mkString)).widen
  ).reduce(_ or _)

  given Decoder[StateDiff] = deriveDecoder[StateDiff]
  given Decoder[ParityAllAccountsInfo.AccountsInfo] = deriveDecoder[ParityAllAccountsInfo.AccountsInfo]
  given Decoder[Mem] = deriveDecoder[Mem]
  given Decoder[Store] = deriveDecoder[Store]
  given Decoder[Ex] = deriveDecoder[Ex]
  given Decoder[VMOperation] = deriveDecoder[VMOperation]
  given Decoder[VMTrace] = deriveDecoder[VMTrace]
  given Decoder[FullTraceInfo] = deriveDecoder[FullTraceInfo]
