package org.web3s.crypto.exception

class CryptoWeb3sException extends RuntimeException:
  
  def this(message: String) =
    this()
    RuntimeException(message)
  
  def this(message: String, cause: Throwable) =
    this()
    RuntimeException(message, cause)
    
end CryptoWeb3sException
