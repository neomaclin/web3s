package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt56:
  val DEFAULT = new UInt56(BigInt(0))
end UInt56

final class UInt56(value: BigInt) extends SolidityUInt(56, value):
  def this(value: Long) = this(BigInt(value))
end UInt56
