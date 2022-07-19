package org.web3s.services.impl

import org.http4s.client.Client
import org.http4s.client.dsl.io._
import org.http4s.syntax.literals.uri
import cats.effect._
import io.circe.syntax._
import org.http4s.{Uri,Method,Header}
import org.http4s.implicits._
import org.web3s.protocol.core.{Request, Response}
import org.web3s.services.Web3sService
import io.circe.generic.auto._
import org.http4s.circe._
import CirceEntityDecoder._
import CirceEntityEncoder._

final case class HttpService[F[_] : Async](uri: Uri = uri"http://localhost:8545/",
                                           client: Client[F],
                                           headers: Header.ToRaw*
                                          ) extends Web3sService[F] :

  def send[T:Decoder](request: Request): F[Response[T]] =
    val posting = Method.POST[Request](request, uri, headers)
    client.expect[Response[T]](posting)


  def sendBatch[T:Decoder](requests: List[Request]): F[List[Response[T]]] =
    val posting = Method.POST[List[Request]](requests, uri, headers)
    client.expect[List[Response[T]]](posting)
