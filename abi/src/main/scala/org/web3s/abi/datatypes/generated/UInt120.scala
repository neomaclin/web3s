package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt120:
  val DEFAULT = new UInt120(BigInt(0))
end UInt120

final class UInt120(value: BigInt) extends SolidityUInt(120, value):
  def this(value: Long) = this(BigInt(value))
end UInt120
