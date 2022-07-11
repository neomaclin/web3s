package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt160:
  val DEFAULT = new UInt160(BigInt(0))
end UInt160

final class UInt160(value: BigInt) extends SolidityUInt(160, value):
  def this(value: Long) = this(BigInt(value))
end UInt160
