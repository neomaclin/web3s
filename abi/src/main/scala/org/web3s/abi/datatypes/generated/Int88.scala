package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int88:
  val DEFAULT = new Int88(BigInt(0))
end Int88

final class Int88(value: BigInt) extends SolidityInt(88, value):
  def this(value: Long) = this(BigInt(value))
end Int88
