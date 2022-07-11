package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt80:
  val DEFAULT = new UInt80(BigInt(0))
end UInt80

final class UInt80(value: BigInt) extends SolidityUInt(80, value):
  def this(value: Long) = this(BigInt(value))
end UInt80
