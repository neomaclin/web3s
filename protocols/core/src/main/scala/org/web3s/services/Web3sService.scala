package org.web3s.services

import io.circe.{Decoder, Encoder}
import org.web3s.protocol.core.{Request, Response}

trait Web3sService[F[_]]:
  def fetch[T: Decoder](request: Request): F[Response[T]]

  def sendBatch[T: Decoder](request: List[Request]): F[List[Response[T]]]
