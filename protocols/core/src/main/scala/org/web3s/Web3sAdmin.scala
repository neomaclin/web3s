package org.web3s

import cats.MonadThrow
import cats.syntax.functor.*
import org.web3s.protocol.admin.Admin
import org.web3s.protocol.admin.methods.response.*
import org.web3s.protocol.admin.methods.response.decoders.given
import org.web3s.protocol.core.methods.request.Transaction
import org.web3s.protocol.core.methods.response.EthTransaction
import org.web3s.protocol.core.methods.response.EthSendTransaction
import org.web3s.protocol.core.{Request, Response}
import org.web3s.services.Web3sService

class Web3sAdmin[F[_] : MonadThrow](using services: Web3sService[F]) extends Admin[F]:
  import io.circe.generic.auto._
  import io.circe.syntax._
  override def personalListAccounts: F[PersonalListAccounts] =
    services.fetch[List[String]](Request(method = "personal_listAccounts")).map(PersonalListAccounts.apply)

  override def personalNewAccount(password: String): F[NewAccountIdentifier] =
    services.fetch[String](Request(method = "personal_newAccount", params = List(password.asJson))).map(NewAccountIdentifier.apply)

  override def personalUnlockAccount(address: String, passphrase: String, duration: Option[BigInt] = None): F[PersonalUnlockAccount] =
    val params = List(address.asJson, passphrase.asJson) ++ duration.map(_.asJson).toList
    services.fetch[Boolean](Request(method = "personal_unlockAccount", params)).map(PersonalUnlockAccount.apply)

  override def personalSendTransaction(transaction: Transaction, password: String): F[EthSendTransaction] =
    services.fetch[String](Request(method = "personal_sendTransaction")).map(EthSendTransaction.apply)

  override def txPoolContent: F[TxPoolContent] =
    import TxPoolContent._
    services.fetch[TxPoolContent.TxPoolContentResult](Request(method = "txpool_content")).map(TxPoolContent.apply)