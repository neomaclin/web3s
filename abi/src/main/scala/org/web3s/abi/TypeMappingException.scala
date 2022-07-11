package org.web3s.abi

class TypeMappingException extends RuntimeException:

  def this(e: Exception) =
    this()
    RuntimeException(e)

  def this(message: String) =
    this()
    RuntimeException(message)

  def this(message: String, e: Exception) =
    this()
    RuntimeException(message, e)

end TypeMappingException
