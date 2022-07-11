package org.web3s.protocol

package exceptions {

  import org.web3s.protocol.core.methods.response.TransactionReceipt

  final case class ClientConnectionException(message: String) extends RuntimeException(message)

  final case class JsonRpcError[T](code: Int, message: String, data: T) extends RuntimeException(message)

  final case class TransactionException(message: String,
                                        transactionHash: Option[String],
                                        transactionReceipt: Option[TransactionReceipt]
                                       ) extends RuntimeException(message)
}
