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

class Web3Geth[F[_] : MonadThrow](services: Web3sService[F]) extends Geth[F]:

  import io.circe._
  import io.circe.syntax._
  import io.circe.generic.auto._
  def personalImportRawKey(keydata: String, password: String): F[PersonalImportRawKey] =
    val param = List(keydata,password).map(_.asJson)
    val request = Request(method = "personal_importRawKey",param)
    services.fetch[String](request).map(PersonalImportRawKey.apply)

  def personalLockAccount(accountId: String): F[BooleanResponse] =
    val param = List(accountId).map(_.asJson)
    val request = Request(method = "personal_lockAccount", param)
    services.fetch[Boolean](request).map(BooleanResponse.apply)


  def personalSign(message: String, accountId: String, password: String): F[PersonalSign] =
    val param = List(message, accountId, password).map(_.asJson)
    val request = Request(method = "personal_sign", param)
    services.fetch[String](request).map(PersonalSign.apply)


  def personalEcRecover(message: String, signature: String): F[PersonalEcRecover] =
    val param = List(message, signature).map(_.asJson)
    val request = Request(method = "personal_ecRecover", param)
    services.fetch[String](request).map(PersonalEcRecover.apply)


  def minerStart(threadCount: Int): F[MinerStartResponse] =
    services.fetch[Unit](Request(method = "miner_start", params = List(threadCount.asJson))).map(MinerStartResponse.apply)

  def minerStop: F[BooleanResponse] =
    services.fetch[Boolean](Request(method = "miner_stop")).map(BooleanResponse.apply)

