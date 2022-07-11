package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt208:
  val DEFAULT = new UInt208(BigInt(0))
end UInt208

final class UInt208(value: BigInt) extends SolidityUInt(208, value):
  def this(value: Long) = this(BigInt(value))
end UInt208
