package org.web3s.tx.exceptions

class ContractCallException extends RuntimeException :

  def this(e: Exception) =
    this()
    RuntimeException(e)

  def this(message: String) =
    this()
    RuntimeException(message)

  def this(message: String, e: Exception) =
    this()
    RuntimeException(message, e)

end ContractCallException
