package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt64:
  val DEFAULT = new UInt64(BigInt(0))
end UInt64

final class UInt64(value: BigInt) extends SolidityUInt(64, value):
  def this(value: Long) = this(BigInt(value))
end UInt64
