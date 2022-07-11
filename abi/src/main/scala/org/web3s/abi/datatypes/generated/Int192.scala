package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int192:
  val DEFAULT = new Int192(BigInt(0))
end Int192

final class Int192(value: BigInt) extends SolidityInt(192, value):
  def this(value: Long) = this(BigInt(value))
end Int192
