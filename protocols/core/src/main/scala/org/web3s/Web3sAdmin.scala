package org.web3s

import cats.effect.Async
import org.web3s.protocol.admin.Admin
import org.web3s.protocol.admin.methods.response.*
import org.web3s.protocol.core.methods.request.Transaction
import org.web3s.protocol.core.methods.response.EthSendTransaction
import org.web3s.protocol.core.Request
import org.web3s.services.Web3sService

class Web3sAdmin[F[_] : Async](services: Web3sService[F]) extends Admin[F] :
  override def personalListAccounts: F[PersonalListAccounts] =
    services.send(Request(method = "personal_listAccounts")).map(PersonalListAccounts.apply)

  override def personalNewAccount(password: String): F[NewAccountIdentifier] =
    services.send(Request(method = "personal_newAccount", params = List(password))).map(NewAccountIdentifier.apply)

  def personalUnlockAccount(address: String, passphrase: String, duration: Option[BigInt] = None): F[PersonalUnlockAccount] =
    services.send(Request(method = "personal_unlockAccount"), params = List(address, passphrase) ++ duration.map(_.toString)).map(PersonalUnlockAccount.apply)

  override def personalSendTransaction(transaction: Transaction, password: String): F[EthSendTransaction] =
    services.send(Request(method = "personal_sendTransaction")).map(EthSendTransaction.apply)

  override def txPoolContent: F[TxPoolContent] =
    services.send(Request(method = "txpool_content")).map(TxPoolContent.apply)