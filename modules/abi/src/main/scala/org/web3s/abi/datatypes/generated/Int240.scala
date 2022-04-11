package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int240:
  val DEFAULT = new Int240(BigInt(0))
end Int240

final class Int240(value: BigInt) extends SolidityInt(240, value):
  def this(value: Long) = this(BigInt(value))
end Int240
