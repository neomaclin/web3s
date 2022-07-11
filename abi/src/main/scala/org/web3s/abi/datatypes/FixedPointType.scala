package org.web3s.abi.datatypes

import org.web3s.abi.datatypes.SolidityType.MAX_BIT_LENGTH

object FixedPointType:
  val DEFAULT_BIT_LENGTH: Int = MAX_BIT_LENGTH >> 1

  private def isValidBitCount(mBitSize: Int, nBitSize: Int, value: BigInt) = value.bitCount <= mBitSize + nBitSize

  def convert(m: BigInt, n: BigInt): BigInt = convert(DEFAULT_BIT_LENGTH, DEFAULT_BIT_LENGTH, m, n)
  
  def convert(mBitSize: Int, nBitSize: Int, m: BigInt, n: BigInt): BigInt =
    val shift = (n.bitLength + 3) & ~0x03
    (m << nBitSize) | (n << (nBitSize - shift))
  end convert

end FixedPointType


abstract class FixedPointType(val typePrefix: String,
                              mBitSize: Int, nBitSize: Int,
                              override val value: BigInt) extends NumericType(typePrefix + mBitSize + "x" + nBitSize, value):
  final val bitSize = mBitSize + nBitSize

  require(valid(mBitSize, nBitSize, value), "Bitsize must be 8 bit aligned, and in range 0 < bitSize <= 256")

  def valid(mBitSize: Int, nBitSize: Int, value: BigInt): Boolean =
    isValidBitSize(mBitSize, nBitSize) && FixedPointType.isValidBitCount(mBitSize, nBitSize, value)

  private def isValidBitSize(mBitSize: Int, nBitSize: Int) =
    mBitSize % 8 == 0 && nBitSize % 8 == 0 && bitSize > 0 && bitSize <= SolidityType.MAX_BIT_LENGTH
    
end FixedPointType
