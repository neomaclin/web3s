package org.web3s.exceptions

/** Encoding exception. */
class MessageEncodingException extends RuntimeException:
  
  def this(message: String, cause: Throwable) = 
    this()
    RuntimeException(message,cause)
  
  def this(message: String) = 
    this()
    RuntimeException(message)

end MessageEncodingException
