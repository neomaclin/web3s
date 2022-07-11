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

final class TxHSMSignService[F[_] : Async, T <: HSMPass](hsmRequestProcessor: HSMRequestProcessor[F], hsmPass: T) extends TxSignService[F] :
  def sign(rawTransaction: RawTransaction, chainId: Long): F[Array[Byte]] =
    Applicative[F].ifA(
      (chainId > ChainIdLong.NONE && rawTransaction.transaction.`type` == Transaction.Type.LEGACY).pure[F]
    )(
      for {
        encodedTransaction <- Async[F].delay(Transaction.Encoder.encode(rawTransaction, chainId))
        messageHash <- Async[F].delay(Hash.sha3(encodedTransaction))
        signatureData <- hsmRequestProcessor.callHSM(messageHash, hsmPass)
        signatureDataEncoded <- Async[F].delay(Transaction.Encoder.createEip155SignatureData(signatureData, chainId))
        bytes <- Async[F].delay(Transaction.Encoder.encode(rawTransaction, signatureDataEncoded))
      } yield bytes
      ,
      for {
        encodedTransaction <- Async[F].delay(Transaction.Encoder.encode(rawTransaction))
        messageHash <- Async[F].delay(Hash.sha3(encodedTransaction))
        signatureData <- hsmRequestProcessor.callHSM(messageHash, hsmPass)
        bytes <- Async[F].delay( Transaction.Encoder.encode(rawTransaction, signatureData))
      } yield bytes
    )

  def address: F[String] = hsmPass.address.pure[F]

