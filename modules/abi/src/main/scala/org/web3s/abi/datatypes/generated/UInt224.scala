package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt224:
  val DEFAULT = new UInt224(BigInt(0))
end UInt224

final class UInt224(value: BigInt) extends SolidityUInt(224, value):
  def this(value: Long) = this(BigInt(value))
end UInt224
