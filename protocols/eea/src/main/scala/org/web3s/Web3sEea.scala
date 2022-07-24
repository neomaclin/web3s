package org.web3s

import cats.MonadThrow
import org.web3s.protocol.eea.Eea
import org.web3s.protocol.core.Request
import org.web3s.protocol.core.methods.response.EthSendTransaction
import org.web3s.services.Web3sService

class Web3sEea[F[_] : MonadThrow](using services: Web3sService[F]) extends Eea[F]:
  override def eeaSendRawTransaction(signedTransactionData: String): F[EthSendTransaction] =
    services.fetch[String](Request(method = "eea_sendRawTransaction")).map(EthSendTransaction.apply)
