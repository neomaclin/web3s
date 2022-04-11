package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt184:
  val DEFAULT = new UInt184(BigInt(0))
end UInt184

final class UInt184(value: BigInt) extends SolidityUInt(184, value):
  def this(value: Long) = this(BigInt(value))
end UInt184
