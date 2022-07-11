
package org.web3s.abi.datatypes

import org.web3s.abi.Encodable

object Fixed:
  val TYPE_NAME = "fixed"
  val DEFAULT = new Fixed(BigInt(0))

  given Encodable[Fixed] = new Encodable[Fixed]:
    override def encode(value: Fixed): String = NumericType.encode(value)
    override def encodePacked(value: Fixed): String = NumericType.encode(value)

end Fixed

final class Fixed(mBitSize: Int,
                  nBitSize: Int,
                  override val value: BigInt) extends FixedPointType(Fixed.TYPE_NAME, mBitSize, nBitSize, value):
  def this(value: BigInt) = this(FixedPointType.DEFAULT_BIT_LENGTH, FixedPointType.DEFAULT_BIT_LENGTH, value)

  def this(m: BigInt, n: BigInt) = this(FixedPointType.convert(m, n))

  def this(mBitSize: Int, nBitSize: Int, m: BigInt, n: BigInt) = this(FixedPointType.convert(mBitSize, nBitSize, m, n))

end Fixed
