package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt40:
  val DEFAULT = new UInt40(BigInt(0))
end UInt40

final class UInt40(value: BigInt) extends SolidityUInt(40, value):
  def this(value: Long) = this(BigInt(value))
end UInt40
