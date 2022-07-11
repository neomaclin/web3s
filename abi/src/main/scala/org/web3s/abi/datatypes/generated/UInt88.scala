package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt88:
  val DEFAULT = new UInt88(BigInt(0))
end UInt88

final class UInt88(value: BigInt) extends SolidityUInt(88, value):
  def this(value: Long) = this(BigInt(value))
end UInt88
