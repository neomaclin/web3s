package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int120:
  val DEFAULT = new Int120(BigInt(0))
end Int120

final class Int120(value: BigInt) extends SolidityInt(120, value):
  def this(value: Long) = this(BigInt(value))
end Int120
