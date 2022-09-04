package org.web3s.protocol.parity

import org.web3s.protocol.core.methods.response.*
import org.web3s.protocol.admin.methods.response.*
import org.web3s.protocol.core.DefaultBlockParameter
import org.web3s.protocol.parity.methods.response.*
import org.web3s.protocol.core.methods.request.*
import org.web3s.protocol.parity.methods.request.*

trait Trace[F[_]]:
  def traceCall(transaction: Transaction, traceTypes: List[String], blockParameter: DefaultBlockParameter): F[ParityFullTraceResponse]

  def traceRawTransaction(data: String, traceTypes: List[String]): F[ParityFullTraceResponse]

  def traceReplayTransaction(hash: String, traceTypes: List[String]): F[ParityFullTraceResponse]

  def traceBlock(blockParameter: DefaultBlockParameter): F[ParityTracesResponse]

  def traceFilter(traceFilter: TraceFilter): F[ParityTracesResponse]

  def traceGet(hash: String, indices: List[BigInt]): F[ParityTraceGet]

  def traceTransaction(hash: String): F[ParityTracesResponse]