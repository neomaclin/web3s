package org.web3s.services.impl

import cats.effect.Async
import org.http4s.client.Client
import org.http4s.{Header, Method, Uri}
import org.web3s.protocol.core.{Request, Response}
import org.web3s.services.Web3sService

final case class HttpService[F[_] : Async](uri: Uri = uri"http://localhost:8545/",
                                           client: Client[F],
                                           headers: Header.ToRaw*
                                          ) extends Web3sService[F] :

  def send[S, T](request: Request[S]): F[Response[T]] =
    val posting = Method.Post[Request[S]](request, uri, headers)
    client.expect[Response[T]](posting)


  def sendBatch[S, T](requests: List[Request[S]]): F[List[Response[T]]] =
    val posting = Method.Post[List[Request[S]]](requests, uri, headers)
    client.expect[List[Response[T]]](posting)
