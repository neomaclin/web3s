
package org.web3s.abi.datatypes

object SolidityType:
  val MAX_BIT_LENGTH: Int = 256
  val MAX_BYTE_LENGTH: Int = MAX_BIT_LENGTH / 8
end SolidityType

trait SolidityType[+T]:

  def bytes32PaddedLength: Int = SolidityType.MAX_BYTE_LENGTH

  def value: T

  def getTypeAsString: String
  
end SolidityType
