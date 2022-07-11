package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int128:
  val DEFAULT = new Int128(BigInt(0))
end Int128

final class Int128(value: BigInt) extends SolidityInt(128, value):
  def this(value: Long) = this(BigInt(value))
end Int128
