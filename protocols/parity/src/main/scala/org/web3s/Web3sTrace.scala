package org.web3s


import cats.MonadThrow
import cats.syntax.functor.*
import org.web3s.services.Web3sService
import org.web3s.protocol.core.methods.response.*
import org.web3s.protocol.core.methods.request.*
import org.web3s.protocol.core.{DefaultBlockParameter, Request}
import org.web3s.protocol.parity.Trace as TraceF
import org.web3s.protocol.parity.methods.request.*
import org.web3s.protocol.parity.methods.response.model.*
import org.web3s.protocol.parity.methods.response.*
import org.web3s.utils.EthBigInt

class Web3sTrace[F[_] : MonadThrow](using services: Web3sService[F]) extends TraceF[F] :

  import io.circe._
  import io.circe.syntax._
  import io.circe.generic.auto._
  import org.web3s.protocol.parity.methods.response.model.given_Decoder_FullTraceInfo
  def traceCall(transaction: Transaction, traceTypes: List[String], blockParameter: DefaultBlockParameter): F[ParityFullTraceResponse] =
    services.fetch[FullTraceInfo](Request(method = "trace_call")).map(ParityFullTraceResponse.apply)

  def traceRawTransaction(data: String, traceTypes: List[String]): F[ParityFullTraceResponse] =
    services.fetch[FullTraceInfo](Request(method = "trace_rawTransaction")).map(ParityFullTraceResponse.apply)

  def traceReplayTransaction(hash: String, traceTypes: List[String]): F[ParityFullTraceResponse] =
    services.fetch[FullTraceInfo](Request(method = "trace_replayTransaction")).map(ParityFullTraceResponse.apply)

  def traceBlock(blockParameter: DefaultBlockParameter): F[ParityTracesResponse] =
    services.fetch[List[Trace]](Request(method = "trace_block")).map(ParityTracesResponse.apply)

  def traceFilter(traceFilter: TraceFilter): F[ParityTracesResponse] =
    services.fetch[List[Trace]](Request(method = "trace_filter",params = List(traceFilter.asJson))).map(ParityTracesResponse.apply)

  def traceGet(hash: String, indices: List[BigInt]): F[ParityTraceGet] =
    services.fetch[Trace](Request(method = "trace_get",params = List(hash.asJson, indices.map(EthBigInt(_)).asJson))).map(ParityTraceGet.apply)

  def traceTransaction(hash: String): F[ParityTracesResponse] =
    services.fetch[List[Trace]](Request(method = "trace_transaction",params = List(hash.asJson))).map(ParityTracesResponse.apply)
