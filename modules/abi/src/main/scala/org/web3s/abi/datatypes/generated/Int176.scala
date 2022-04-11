package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int176:
  val DEFAULT = new Int176(BigInt(0))
end Int176

final class Int176(value: BigInt) extends SolidityInt(176, value):
  def this(value: Long) = this(BigInt(value))
end Int176
