package org.web3s.protocol.core.methods.response

import io.circe.Decoder
import org.web3s.protocol.core.Response


opaque type EthLog = Response[List[EthLog.LogResult]]

object EthLog:
  sealed trait LogResult
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
                      ) extends LogResult


  final case class Hash(value: String) extends LogResult
  import cats.syntax.functor._
  import io.circe._
  import io.circe.syntax._
  import io.circe.generic.auto._
  given Decoder[LogResult] = List[Decoder[LogResult]](
    Decoder[Log].widen,
    Decoder[Hash].widen,
  ).reduceLeft(_ or _)

  def apply(responses: Response[List[EthLog.LogResult]]): EthLog = responses

extension (x: EthLog)
  def logs: List[EthLog.LogResult] = x.result
