package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt168:
  val DEFAULT = new UInt168(BigInt(0))
end UInt168

final class UInt168(value: BigInt) extends SolidityUInt(168, value):
  def this(value: Long) = this(BigInt(value))
end UInt168
