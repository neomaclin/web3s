package org.web3s.ens.exceptions

class EnsResolutionException extends RuntimeException:

  def this(e: Exception) =
    this()
    RuntimeException(e)

  def this(message: String) =
    this()
    RuntimeException(message)

  def this(message: String, e: Exception) =
    this()
    RuntimeException(message, e)
