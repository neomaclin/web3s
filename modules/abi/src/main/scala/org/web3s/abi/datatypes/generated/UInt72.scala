package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt72:
  val DEFAULT = new UInt72(BigInt(0))
end UInt72

final class UInt72(value: BigInt) extends SolidityUInt(72, value):
  def this(value: Long) = this(BigInt(value))
end UInt72
