package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int104:
  val DEFAULT = new Int104(BigInt(0))
end Int104

final class Int104(value: BigInt) extends SolidityInt(104, value):
  def this(value: Long) = this(BigInt(value))
end Int104
