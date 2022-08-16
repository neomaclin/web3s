package org.web3s

import cats.MonadThrow
import cats.syntax.functor._
import org.web3s.protocol.eea.Eea
import org.web3s.protocol.core.Request
import org.web3s.protocol.core.methods.response.EthSendTransaction
import org.web3s.services.Web3sService

class Web3sEea[F[_] : MonadThrow](services: Web3sService[F]) extends Eea[F]:

  import io.circe._
  import io.circe.syntax._
  import io.circe.generic.auto._
  override def eeaSendRawTransaction(signedTransactionData: String): F[EthSendTransaction] =
    services.fetch[String](Request(method = "eea_sendRawTransaction",params = List(signedTransactionData.asJson))).map(EthSendTransaction.apply)
