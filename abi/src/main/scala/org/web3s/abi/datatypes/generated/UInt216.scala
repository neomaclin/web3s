package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt216:
  val DEFAULT = new UInt216(BigInt(0))
end UInt216

final class UInt216(value: BigInt) extends SolidityUInt(216, value):
  def this(value: Long) = this(BigInt(value))
end UInt216
