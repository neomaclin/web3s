package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int256:
  val DEFAULT = new Int256(BigInt(0))
end Int256

final class Int256(value: BigInt) extends SolidityInt(256, value):
  def this(value: Long) = this(BigInt(value))
end Int256
