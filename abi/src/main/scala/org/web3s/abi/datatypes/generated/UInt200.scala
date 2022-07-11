package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt200:
  val DEFAULT = new UInt200(BigInt(0))
end UInt200

final class UInt200(value: BigInt) extends SolidityUInt(200, value):
  def this(value: Long) = this(BigInt(value))
end UInt200
