package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt104:
  val DEFAULT = new UInt104(BigInt(0))
end UInt104

final class UInt104(value: BigInt) extends SolidityUInt(104, value):
  def this(value: Long) = this(BigInt(value))
end UInt104
