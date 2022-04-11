package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt16:
  val DEFAULT = new UInt16(BigInt(0))
end UInt16

final class UInt16(value: BigInt) extends SolidityUInt(16, value):
  def this(value: Long) = this(BigInt(value))
end UInt16
