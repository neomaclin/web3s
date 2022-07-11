package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int24:
  val DEFAULT = new Int24(BigInt(0))
end Int24

final class Int24(value: BigInt) extends SolidityInt(24, value):
  def this(value: Long) = this(BigInt(value))
end Int24
