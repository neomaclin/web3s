package org.web3s.crypto.exception

class CipherException extends Exception :
  
  def this(message: String) =
    this()
    Exception(message)
  
  def this(cause: Throwable) =
    this()
    Exception(cause)
  
  def this(message: String, cause: Throwable) =
    this()
    Exception(message, cause)

end CipherException
