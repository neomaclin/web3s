package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt144:
  val DEFAULT = new UInt144(BigInt(0))
end UInt144

final class UInt144(value: BigInt) extends SolidityUInt(144, value):
  def this(value: Long) = this(BigInt(value))
end UInt144
