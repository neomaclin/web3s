
package org.web3s.abi.datatypes

import org.web3s.abi.datatypes.FixedPointType.DEFAULT_BIT_LENGTH

object Ufixed:
  val TYPE_NAME = "ufixed"
  val DEFAULT = new Ufixed(BigInt(0))
end Ufixed


final class Ufixed(val mBitSize: Int,
                   val nBitSize: Int,
                   override val value: BigInt) extends FixedPointType(Ufixed.TYPE_NAME, mBitSize, nBitSize, value) :
  def this(value: BigInt) = this(DEFAULT_BIT_LENGTH, DEFAULT_BIT_LENGTH, value)

  def this(m: BigInt, n: BigInt) = this(FixedPointType.convert(m, n))

  def this(mBitSize: Int, nBitSize: Int, m: BigInt, n: BigInt) = this(FixedPointType.convert(mBitSize, nBitSize, m, n))

  override def valid(mBitSize: Int, nBitSize: Int, value: BigInt): Boolean = super.valid(mBitSize, nBitSize, value) && value.signum != -1

end Ufixed
