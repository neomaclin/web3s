package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int208:
  val DEFAULT = new Int208(BigInt(0))
end Int208

final class Int208(value: BigInt) extends SolidityInt(208, value):
  def this(value: Long) = this(BigInt(value))
end Int208
