package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int248:
  val DEFAULT = new Int248(BigInt(0))
end Int248

final class Int248(value: BigInt) extends SolidityInt(248, value):
  def this(value: Long) = this(BigInt(value))
end Int248
