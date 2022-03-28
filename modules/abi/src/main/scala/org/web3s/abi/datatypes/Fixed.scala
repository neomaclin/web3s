
package org.web3s.abi.datatypes

object Fixed:
  val TYPE_NAME = "fixed"
  val DEFAULT = new Fixed(BigInt(0))
end Fixed

final class Fixed(mBitSize: Int,
                  nBitSize: Int,
                  override val value: BigInt) extends FixedPointType(Fixed.TYPE_NAME, mBitSize, nBitSize, value):
  def this(value: BigInt) = this(FixedPointType.DEFAULT_BIT_LENGTH, FixedPointType.DEFAULT_BIT_LENGTH, value)

  def this(m: BigInt, n: BigInt) = this(FixedPointType.convert(m, n))

  def this(mBitSize: Int, nBitSize: Int, m: BigInt, n: BigInt) = this(FixedPointType.convert(mBitSize, nBitSize, m, n))

end Fixed
