package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt152:
  val DEFAULT = new UInt152(BigInt(0))
end UInt152

final class UInt152(value: BigInt) extends SolidityUInt(152, value):
  def this(value: Long) = this(BigInt(value))
end UInt152
