package org.web3s.protocol.parity.methods.response.model


final case class Trace(action: Trace.Action,
                       error: Option[String],
                       result: Option[Trace.Result],
                       subtraces: BigInt,
                       traceAddress: List[BigInt],
                       `type`: String,
                       blockHash: Option[String],
                       blockNumber: Option[BigInt],
                       transactionHash: Option[String],
                       transactionPosition:Option[BigInt])

object Trace:
  final case class Result(address: String, code: String, gasUsed: String, output: Option[String])

  enum Action:
    case SuicideAction(address: String, balance: String, refundAddress: String) extends Action
    case CallAction(callType: String, from: String, to: String, gas: String, input: String, value: String) extends Action
    case CreateAction(from: String, gas: String, value: String, init: String) extends Action
    case RewardAction(author: String, value: String, rewardType: String) extends Action
