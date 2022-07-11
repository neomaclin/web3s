package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int72:
  val DEFAULT = new Int72(BigInt(0))
end Int72

final class Int72(value: BigInt) extends SolidityInt(72, value):
  def this(value: Long) = this(BigInt(value))
end Int72
