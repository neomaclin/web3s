package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt256:
  val DEFAULT = new UInt256(BigInt(0))
end UInt256

final class UInt256(value: BigInt) extends SolidityUInt(256, value):
  def this(value: Long) = this(BigInt(value))
end UInt256
