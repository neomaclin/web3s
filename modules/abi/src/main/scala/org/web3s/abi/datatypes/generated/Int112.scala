package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int112:
  val DEFAULT = new Int112(BigInt(0))
end Int112

final class Int112(value: BigInt) extends SolidityInt(112, value):
  def this(value: Long) = this(BigInt(value))
end Int112
