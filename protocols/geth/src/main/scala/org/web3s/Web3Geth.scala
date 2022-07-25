package org.web3s

import cats.MonadThrow
import cats.syntax.functor._
import org.web3s.protocol.geth.Geth
import org.web3s.protocol.core.Request
import org.web3s.protocol.core.methods.response.EthSendTransaction
import org.web3s.services.Web3sService

import org.web3s.protocol.core.methods.response.BooleanResponse
import org.web3s.protocol.admin.methods.response.PersonalSign
import org.web3s.protocol.core.methods.response.MinerStartResponse
import org.web3s.protocol.geth.methods.response.PersonalEcRecover
import org.web3s.protocol.geth.methods.response.PersonalImportRawKey

class Web3Geth[F[_] : MonadThrow](using services: Web3sService[F]) extends Geth[F] :
  def personalImportRawKey(keydata: String, password: String): F[PersonalImportRawKey] =
    services.fetch[String](Request(method = "personal_importRawKey")).map(PersonalImportRawKey.apply)

  def personalLockAccount(accountId: String): F[BooleanResponse] =
    services.fetch[Boolean](Request(method = "personal_lockAccount")).map(BooleanResponse.apply)


  def personalSign(message: String, accountId: String, password: String): F[PersonalSign] =
    services.fetch[String](Request(method = "personal_sign")).map(PersonalSign.apply)


  def personalEcRecover(message: String, signiture: String): F[PersonalEcRecover] =
    services.fetch[String](Request(method = "personal_ecRecover")).map(PersonalEcRecover.apply)


  def minerStart(threadCount: Int): F[MinerStartResponse] =
    services.fetch[Unit](Request(method = "miner_start")).map(MinerStartResponse.apply)

  def minerStop: F[BooleanResponse] =
    services.fetch[Boolean](Request(method = "miner_stop")).map(BooleanResponse.apply)

