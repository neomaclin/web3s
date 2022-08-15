package org.web3s.services.impl

import org.http4s.client.Client
import org.http4s.client.dsl.Http4sClientDsl
import org.http4s.syntax.literals.uri
import cats.effect.*
import io.circe.Decoder.Result
import io.circe.{Decoder, HCursor}
import io.circe.generic.semiauto.deriveDecoder
import org.http4s.{Header, Method, Uri}
import org.http4s.implicits.*
import org.web3s.protocol.core.{Request, Response}
import org.web3s.services.Web3sService
import fs2.Stream


final case class Http4sWeb3sService[F[_] : Async: Concurrent](uri: Uri = uri"http://localhost:8545/",
                                                              client: Client[F],
                                                              dsl: Http4sClientDsl[F],
                                                              headers: Header.ToRaw*
                                          ) extends Web3sService[F] :

  import io.circe.{Encoder,Decoder}
  import io.circe.syntax._

  import io.circe.generic.semiauto._
  import org.http4s.circe._
  import CirceEntityDecoder._
  import CirceEntityEncoder._
  import org.web3s.protocol.core.methods.request.encoder.given

  given Encoder[Request] = deriveEncoder[Request]

  def fetch[T:Decoder](request: Request): F[Response[T]] =
    import dsl._
    given Decoder[Response[T]] = Response.decode[T]
    client.expect(Method.POST[Request](request, uri, headers))(jsonOf[F,Response[T]])


  def fetchBatch[T:Decoder](requests: List[Request]): F[List[Response[T]]] =
    import dsl._
    given Decoder[Response[T]] = Response.decode[T]
    client.expect[List[Response[T]]](Method.POST[List[Request]](requests, uri, headers))

  def fetchStream[T:Decoder](request: Request): Stream[F,T] = ???
//    import dsl._
//    client.stream(request)
