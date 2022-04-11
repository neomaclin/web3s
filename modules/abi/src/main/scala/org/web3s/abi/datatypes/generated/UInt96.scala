package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt96:
  val DEFAULT = new UInt96(BigInt(0))
end UInt96

final class UInt96(value: BigInt) extends SolidityUInt(96, value):
  def this(value: Long) = this(BigInt(value))
end UInt96
