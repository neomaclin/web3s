package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt240:
  val DEFAULT = new UInt240(BigInt(0))
end UInt240

final class UInt240(value: BigInt) extends SolidityUInt(240, value):
  def this(value: Long) = this(BigInt(value))
end UInt240
