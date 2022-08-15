package org.web3s.protocol.geth

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.protocol.core.Web3sServiceRequestJsonTest

class RequestTest extends AnyFunSuite :

  import io.circe._
  import io.circe.syntax._
  import io.circe.generic.auto._
  import io.circe.parser._

  private val web3sServiceRequestJsonTest = new Web3sServiceRequestJsonTest