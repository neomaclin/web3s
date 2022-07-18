package org.web3s.services

import io.circe.{Decoder, Encoder}
import org.web3s.protocol.core.{Request, Response}

trait Web3sService[F[_]]:
  def send[S: Encoder, T: Decoder](request: Request[S]): F[Response[T]]

  def sendBatch[S: Encoder, T: Decoder](request: List[Request[S]]): F[List[Response[T]]]
