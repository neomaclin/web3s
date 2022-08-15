package org.web3s.protocol.core


import io.circe.{Decoder, Encoder, Json}
import io.circe.syntax.*
import io.circe.generic.semiauto.*
import org.http4s.circe.*
import CirceEntityDecoder.*
import CirceEntityEncoder.*
import io.circe.parser.parse
import org.scalatest.Assertions
import org.scalatest.compatible.Assertion
import org.web3s.services.Web3sService
import org.scalatestplus.mockito.MockitoSugar

import scala.util.*

class Web3sServiceRequestJsonTest extends Web3sService[Try] with MockitoSugar with Assertions:

  given Encoder[Request] = deriveEncoder[Request]
  private var requestJson: Option[Json] = None

  def fetch[T: Decoder](request: Request): Try[Response[T]] =
    requestJson = Some(request.asJson)
    mock[Try[Response[T]]]


  def fetchBatch[T: Decoder](requests: List[Request]): Try[List[Response[T]]] =
    requestJson = Some(requests.asJson)
    mock[Try[List[Response[T]]]]

  def verifyResult(expected:String): Assertion =
    Request.nextId.getAndDecrement()
    assert(parse(expected).toOption == requestJson )