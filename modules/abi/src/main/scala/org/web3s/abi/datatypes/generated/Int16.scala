package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int16:
  val DEFAULT = new Int16(BigInt(0))
end Int16

final class Int16(value: BigInt) extends SolidityInt(16, value):
  def this(value: Long) = this(BigInt(value))
end Int16
