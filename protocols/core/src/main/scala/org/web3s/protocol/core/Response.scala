package org.web3s.protocol.core

import io.circe.Decoder.Result
import io.circe.{Decoder, HCursor}
import io.circe.generic.semiauto._

final case class Response[T: Decoder](id: Long,
                                      jsonrpc: String,
                                      result: T,
                                      error: Response.Error)

object Response {
  opaque type EthBigInt = BigInt

  object EthBigInt:
    def apply(value: BigInt): EthBigInt = value

  given Decoder[EthBigInt] = Decoder.decodeString.emapTry(org.web3s.utils.Numeric.decodeQuantity).map(EthBigInt.apply)

  private given Decoder[Error] = deriveDecoder

  def decode[T: Decoder]: Decoder[Response[T]] = (c: HCursor) =>
    for
      id <- c.downField("id").as[Long]
      jsonrpc <- c.downField("jsonrpc").as[String]
      result <- c.downField("result").as[T]
      error <- c.downField("id").as[Error]
    yield
      Response[T](id, jsonrpc, result, error)


  final case class Error(code: Int, message: String, data: String)
}