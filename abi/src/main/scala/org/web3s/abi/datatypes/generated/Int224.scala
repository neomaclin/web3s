package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int224:
  val DEFAULT = new Int224(BigInt(0))
end Int224

final class Int224(value: BigInt) extends SolidityInt(224, value):
  def this(value: Long) = this(BigInt(value))
end Int224
