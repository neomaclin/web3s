package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt128:
  val DEFAULT = new UInt128(BigInt(0))
end UInt128

final class UInt128(value: BigInt) extends SolidityUInt(128, value):
  def this(value: Long) = this(BigInt(value))
end UInt128
