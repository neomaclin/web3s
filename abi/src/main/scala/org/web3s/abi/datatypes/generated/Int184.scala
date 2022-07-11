package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int184:
  val DEFAULT = new Int184(BigInt(0))
end Int184

final class Int184(value: BigInt) extends SolidityInt(184, value):
  def this(value: Long) = this(BigInt(value))
end Int184
