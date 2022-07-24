package org.web3s.protocol.parity

trait Trace[F[_]]:
  def traceCall(transaction: Transaction, traceTypes: List[String], blockParameter: DefaultBlockParameter): F[ParityFullTraceResponse]

  def traceRawTransaction(data: String, traceTypes: List[String]): F[ParityFullTraceResponse]

  def traceReplayTransaction(hash: String, traceTypes: List[String]): F[ParityFullTraceResponse]

  def traceBlock(blockParameter: DefaultBlockParameter): F[ParityTracesResponse]

  def traceFilter(traceFilter: TraceFilter): F[ParityTracesResponse]

  def traceGet(hash: String, indices: List[BigInt]): F[ParityTraceGet]

  def traceTransaction(hash: String): F[ParityTracesResponse]
