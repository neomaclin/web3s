package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt8:
  val DEFAULT = new UInt8(BigInt(0))
end UInt8

final class UInt8(value: BigInt) extends SolidityUInt(8, value):
  def this(value: Long) = this(BigInt(value))
end UInt8
