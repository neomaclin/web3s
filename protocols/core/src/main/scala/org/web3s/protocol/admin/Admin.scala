package org.web3s.protocol.admin

import methods.response.*
import org.web3s.protocol.core.methods.request.Transaction
import org.web3s.protocol.core.methods.response.EthSendTransaction

trait Admin[F[_]]:

  def personalListAccounts: F[PersonalListAccounts]

  def personalNewAccount(password: String): F[NewAccountIdentifier]

  def personalUnlockAccount(address: String, passphrase: String, duration: Option[BigInt] = None): F[PersonalUnlockAccount]

  def personalSendTransaction(transaction: Transaction, password: String): F[EthSendTransaction]

  def txPoolContent: F[TxPoolContent]

