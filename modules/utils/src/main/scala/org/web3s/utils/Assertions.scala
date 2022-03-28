
package org.web3s.utils

/** Assertion utility functions. */
object Assertions:
  
  @throws[RuntimeException]
  def verifyPrecondition(assertionResult: Boolean, errorMessage: String): Unit =
    if (!assertionResult) throw new RuntimeException(errorMessage)
    
end Assertions
