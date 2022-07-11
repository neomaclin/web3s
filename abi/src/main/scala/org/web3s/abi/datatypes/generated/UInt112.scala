package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt112:
  val DEFAULT = new UInt112(BigInt(0))
end UInt112

final class UInt112(value: BigInt) extends SolidityUInt(112, value):
  def this(value: Long) = this(BigInt(value))
end UInt112
