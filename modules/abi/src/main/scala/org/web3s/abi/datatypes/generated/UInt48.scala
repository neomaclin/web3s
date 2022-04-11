package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityUInt

object UInt48:
  val DEFAULT = new UInt48(BigInt(0))
end UInt48

final class UInt48(value: BigInt) extends SolidityUInt(48, value):
  def this(value: Long) = this(BigInt(value))
end UInt48
