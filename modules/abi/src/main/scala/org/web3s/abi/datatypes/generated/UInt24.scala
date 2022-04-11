package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt24:
  val DEFAULT = new UInt24(BigInt(0))
end UInt24

final class UInt24(value: BigInt) extends SolidityUInt(24, value):
  def this(value: Long) = this(BigInt(value))
end UInt24
