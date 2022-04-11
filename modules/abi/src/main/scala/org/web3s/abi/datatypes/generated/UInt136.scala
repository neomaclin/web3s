package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt136:
  val DEFAULT = new UInt136(BigInt(0))
end UInt136

final class UInt136(value: BigInt) extends SolidityUInt(136, value):
  def this(value: Long) = this(BigInt(value))
end UInt136
