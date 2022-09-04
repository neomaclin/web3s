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

class Web3sTrace[F[_] : MonadThrow](services: Web3sService[F]) extends TraceF[F] :

  import io.circe._
  import io.circe.syntax._
  import io.circe.generic.auto._
  import org.web3s.protocol.parity.methods.response.decoders.given
  import org.web3s.protocol.core.methods.response.decoders.given
  import org.web3s.protocol.core.methods.request.encoder.given

  def traceCall(transaction: Transaction, traceTypes: List[String], blockParameter: DefaultBlockParameter): F[ParityFullTraceResponse] =
    val params = List(transaction.asJson.dropNullValues, traceTypes.asJson, blockParameter.asJson)
    val request = Request(method = "trace_call", params)
    services.fetch[FullTraceInfo](request).map(ParityFullTraceResponse.apply)

  def traceRawTransaction(data: String, traceTypes: List[String]): F[ParityFullTraceResponse] =
    val params = List(data.asJson, traceTypes.asJson)
    val request = Request(method = "trace_rawTransaction", params)
    services.fetch[FullTraceInfo](request).map(ParityFullTraceResponse.apply)

  def traceReplayTransaction(hash: String, traceTypes: List[String]): F[ParityFullTraceResponse] =
    val params = List(hash.asJson, traceTypes.asJson)
    val request = Request(method = "trace_replayTransaction", params)
    services.fetch[FullTraceInfo](request).map(ParityFullTraceResponse.apply)

  def traceBlock(blockParameter: DefaultBlockParameter): F[ParityTracesResponse] =
    val params = List(blockParameter.asJson)
    val request = Request(method = "trace_block", params)
    services.fetch[List[Trace]](request).map(ParityTracesResponse.apply)

  def traceFilter(traceFilter: TraceFilter): F[ParityTracesResponse] =
    val params = List(traceFilter.asJson)
    val request = Request(method = "trace_filter", params)
    services.fetch[List[Trace]](request).map(ParityTracesResponse.apply)

  def traceGet(hash: String, indices: List[BigInt]): F[ParityTraceGet] =
    val params = List(hash.asJson, indices.asJson)
    val request = Request(method = "trace_get", params)
    services.fetch[Trace](request).map(ParityTraceGet.apply)

  def traceTransaction(hash: String): F[ParityTracesResponse] =
    val params = List(hash.asJson)
    val request = Request(method = "trace_transaction", params)
    services.fetch[List[Trace]](request).map(ParityTracesResponse.apply)
