package org.web3s.abi.datatypes

import org.web3s.abi.Encodable
import org.web3s.abi.datatypes.SolidityType.MAX_BIT_LENGTH

object SolidityUInt:
  
  val TYPE_NAME = "uint"
  val DEFAULT = new SolidityUInt(BigInt(0))
  
  given Encodable[SolidityUInt] = NumericType.encode(_)
  
end SolidityUInt


class SolidityUInt(override val bitSize: Int,
                   override val value: BigInt) extends IntType(SolidityUInt.TYPE_NAME, bitSize, value):
  
  def this(value: BigInt) = this(MAX_BIT_LENGTH, value)// "int" values should be declared as int256 in computing function selectors
  
  override def valid: Boolean = super.valid && 0 <= value.signum

end SolidityUInt
