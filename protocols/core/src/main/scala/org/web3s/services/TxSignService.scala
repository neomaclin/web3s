package org.web3s.services

import cats.Applicative
import cats.effect.Async
import cats.implicits._
import org.web3s.crypto.HSMPass
import org.web3s.crypto.Hash
import org.web3s.crypto.transaction.{RawTransaction, Transaction}

import org.web3s.tx.ChainIdLong

trait TxSignService[F[_]]:
  def sign(rawTransaction: RawTransaction, chainId: Long): F[Array[Byte]]

  def address: F[String]
