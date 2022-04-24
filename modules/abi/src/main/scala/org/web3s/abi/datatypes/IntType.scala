package org.web3s.abi.datatypes

import org.web3s.abi.datatypes.SolidityType.MAX_BIT_LENGTH

object IntType:

  private def isValidBitSize(bitSize: Int) = bitSize % 8 == 0 && bitSize > 0 && bitSize <= MAX_BIT_LENGTH

  private def isValidBitCount(bitSize: Int, value: BigInt) = value.bitLength <= bitSize

end IntType


abstract class IntType(val typePrefix: String,
                       override val bitSize: Int,
                       override val value: BigInt) extends NumericType(typePrefix + bitSize, value) :
  require(valid, "Bit size must be 8 bit aligned, " + "and in range 0 < bitSize <= " + MAX_BIT_LENGTH)

  def valid: Boolean = IntType.isValidBitSize(bitSize) && IntType.isValidBitCount(bitSize, value)

end IntType
