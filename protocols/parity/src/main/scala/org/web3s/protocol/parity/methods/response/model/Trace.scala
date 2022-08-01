package org.web3s.protocol.parity.methods.response.model


final case class Trace(action: Trace.Action,
                       error: String,
                       result: Trace.Result,
                       subtraces: BigInt,
                       traceAddress: List[BigInt],
                       `type`: String,
                       blockHash: String,
                       blockNumber: BigInt,
                       transactionHash: String,
                       transactionPosition: BigInt)

object Trace:

  import io.circe.Decoder
  import io.circe.syntax._
  import io.circe.generic.auto._
  import io.circe.generic.semiauto


  given Decoder[Result] = semiauto.deriveDecoder
  given Decoder[Trace] = semiauto.deriveDecoder

  final case class Result(address: String, code: String, gasUsed: String, output: String)

  enum Action:
    case SuicideAction(address: String, balance: String, refundAddress: String) extends Action
    case CallAction(from: String, to: String, gas: String, input: String, value: String) extends Action
    case CreateAction(from: String, gas: String, value: String, init: String) extends Action
    case RewardAction(author: String, value: String, rewardType: String) extends Action
