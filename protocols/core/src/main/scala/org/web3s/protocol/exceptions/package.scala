package org.web3s.protocol

import core.methods.response.model.TransactionReceipt

package exceptions:

  final case class ClientConnectionException(message: String) extends RuntimeException(message)

  final case class JsonRpcError[T](code: Int, message: String, data: T) extends RuntimeException(message)

  final case class TransactionException(message: String,
                                        transactionHash: Option[String],
                                        transactionReceipt: Option[TransactionReceipt]
                                       ) extends RuntimeException(message)

