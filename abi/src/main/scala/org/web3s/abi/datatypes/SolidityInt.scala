
package org.web3s.abi.datatypes

import org.web3s.abi.datatypes.SolidityType.MAX_BIT_LENGTH

object SolidityInt:
  val TYPE_NAME = "int"
  val DEFAULT = new SolidityInt(BigInt(0))
end SolidityInt

class SolidityInt(override val bitSize: Int,
                  override val value: BigInt) extends IntType(SolidityInt.TYPE_NAME, bitSize, value) :
  def this(value: BigInt) = this(MAX_BIT_LENGTH, value) // "int" values should be declared as int256 in computing function selectors

end SolidityInt