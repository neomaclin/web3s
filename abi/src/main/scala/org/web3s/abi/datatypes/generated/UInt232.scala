package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt232:
  val DEFAULT = new UInt232(BigInt(0))
end UInt232

final class UInt232(value: BigInt) extends SolidityUInt(232, value):
  def this(value: Long) = this(BigInt(value))
end UInt232
