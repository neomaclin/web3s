package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt248:
  val DEFAULT = new UInt248(BigInt(0))
end UInt248

final class UInt248(value: BigInt) extends SolidityUInt(248, value):
  def this(value: Long) = this(BigInt(value))
end UInt248
