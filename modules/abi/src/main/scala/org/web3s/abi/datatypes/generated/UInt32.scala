package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt32:
  val DEFAULT = new UInt32(BigInt(0))
end UInt32

final class UInt32(value: BigInt) extends SolidityUInt(32, value):
  def this(value: Long) = this(BigInt(value))
end UInt32
