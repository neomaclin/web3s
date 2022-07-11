package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt192:
  val DEFAULT = new UInt192(BigInt(0))
end UInt192

final class UInt192(value: BigInt) extends SolidityUInt(192, value):
  def this(value: Long) = this(BigInt(value))
end UInt192
