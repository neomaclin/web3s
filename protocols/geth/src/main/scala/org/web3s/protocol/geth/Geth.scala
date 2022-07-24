package org.web3s.protocol.geth

import org.web3s.protocol.core.methods.response.BooleanResponse
import org.web3s.protocol.admin.methods.response.PersonalSign
import org.web3s.protocol.core.methods.response.MinerStartResponse
import org.web3s.protocol.geth.methods.response.PersonalEcRecover
import org.web3s.protocol.geth.methods.response.PersonalImportRawKey

trait Geth[F[_]]:
  def personalImportRawKey(keydata: String, password: String): F[PersonalImportRawKey]

  def personalLockAccount(accountId: String): F[BooleanResponse]

  def personalSign(message: String, accountId: String, password: String): F[PersonalSign]

  def personalEcRecover(message: String, signiture: String): F[PersonalEcRecover]

  def minerStart(threadCount: Int): F[MinerStartResponse]

  def minerStop: F[BooleanResponse]


