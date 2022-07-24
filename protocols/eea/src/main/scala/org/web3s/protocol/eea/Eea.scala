package org.web3s.protocol.eea
import org.web3s.protocol.core.methods.response.EthSendTransaction
trait Eea[F[_]]:
  def eeaSendRawTransaction(signedTransactionData: String): F[EthSendTransaction]

