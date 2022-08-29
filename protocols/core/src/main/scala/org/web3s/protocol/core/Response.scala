package org.web3s.protocol.core

import io.circe.Decoder.Result
import io.circe.{Decoder, HCursor}

import org.web3s.utils.Numeric

import scala.util.Try

final case class Response[T: Decoder](id: Option[Int],
                                      jsonrpc: Option[String],
                                      result: T,
                                      error: Option[Response.Error])

object Response {
  import io.circe.generic.semiauto.*

  given Decoder[Error] = deriveDecoder
//  given decode[T: Decoder]: Decoder[Response[T]] = deriveDecoder[Response[T]]
  given decode[T: Decoder]: Decoder[Response[T]] = (c: HCursor) =>
    for
      id <- c.downField("id").as[Option[Int]]
      jsonrpc <- c.downField("jsonrpc").as[Option[String]]
      result <- c.downField("result").as[T]
      error <- c.downField("error").as[Option[Error]]
    yield
      Response[T](id, jsonrpc, result, error)


  final case class Error(code: Int, message: String, data: Option[String])
}