package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt176:
  val DEFAULT = new UInt176(BigInt(0))
end UInt176

final class UInt176(value: BigInt) extends SolidityUInt(176, value):
  def this(value: Long) = this(BigInt(value))
end UInt176
