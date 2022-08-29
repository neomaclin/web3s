package org.web3s.protocol.core

import org.http4s.circe.*
import org.http4s.circe.CirceEntityDecoder.*
import org.http4s.circe.CirceEntityEncoder.*
import org.scalatest.Assertions
import org.scalatest.compatible.Assertion
import org.scalatestplus.mockito.MockitoSugar
import org.web3s.services.Web3sService

import scala.util.*

class Web3sServiceResponseJsonTest extends Web3sService[Try] with MockitoSugar with Assertions:


  import io.circe.generic.auto.*
  import io.circe.parser.parse
  import io.circe.syntax.*
  import io.circe.{Decoder, Encoder, Json}
  import Response.given

  private var responseJson: Option[Json] = None

  def fetch[T: Decoder](request: Request): Try[Response[T]] =
    responseJson.get.as[Response[T]].toTry

  def fetchBatch[T: Decoder](requests: List[Request]): Try[List[Response[T]]] =
    responseJson.get.as[List[Response[T]]].toTry

  def buildResponse(expected:String): Unit =
    this.responseJson = parse(expected).toOption
