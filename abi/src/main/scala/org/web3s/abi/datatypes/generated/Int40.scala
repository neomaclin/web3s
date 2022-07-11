package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int40:
  val DEFAULT = new Int40(BigInt(0))
end Int40

final class Int40(value: BigInt) extends SolidityInt(40, value):
  def this(value: Long) = this(BigInt(value))
end Int40
