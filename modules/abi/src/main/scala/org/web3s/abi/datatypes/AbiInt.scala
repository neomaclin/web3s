
package org.web3s.abi.datatypes

import org.web3s.abi.datatypes.AbiType.MAX_BIT_LENGTH

object AbiInt:
  val TYPE_NAME = "int"
  val DEFAULT = new AbiInt(BigInt(0))
end AbiInt

final class AbiInt(override val bitSize: Int,
                   override val value: BigInt) extends IntType(AbiInt.TYPE_NAME, bitSize, value) :
  def this(value: BigInt) = this(MAX_BIT_LENGTH, value) // "int" values should be declared as int256 in computing function selectors
   
end AbiInt