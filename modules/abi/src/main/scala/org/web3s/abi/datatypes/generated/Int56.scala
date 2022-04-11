package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int56:
  val DEFAULT = new Int56(BigInt(0))
end Int56

final class Int56(value: BigInt) extends SolidityInt(56, value):
  def this(value: Long) = this(BigInt(value))
end Int56
