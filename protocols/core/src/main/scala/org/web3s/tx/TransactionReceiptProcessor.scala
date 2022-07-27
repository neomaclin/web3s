package org.web3s.tx

import cats.MonadThrow
import org.web3s.protocol.core.methods.response.model.TransactionReceipt

trait TransactionReceiptProcessor[F[_] : MonadThrow]:
  def sendTransactionReceiptRequest(transactionHash: String): F[Option[TransactionReceipt]]

