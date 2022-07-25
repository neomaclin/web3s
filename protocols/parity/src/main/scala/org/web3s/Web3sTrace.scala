//package org.web3s
//
//import cats.MonadThrow
//import org.web3s.services.Web3sService
////
////class Web3sTrace[F[_]: MonadThrow](using services: Web3sService[F]) extends Trace[F]
////def traceCall(transaction: Transaction, traceTypes: List[String], blockParameter: DefaultBlockParameter): F[ParityFullTraceResponse]
////
////def traceRawTransaction(data: String, traceTypes: List[String]): F[ParityFullTraceResponse]
////
////def traceReplayTransaction(hash: String, traceTypes: List[String]): F[ParityFullTraceResponse]
////
////def traceBlock(blockParameter: DefaultBlockParameter): F[ParityTracesResponse]
////
////def traceFilter(traceFilter: TraceFilter): F[ParityTracesResponse]
////
////def traceGet(hash: String, indices: List[BigInt]): F[ParityTraceGet]
////
////def traceTransaction(hash: String): F[ParityTracesResponse]