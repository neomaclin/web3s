package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int168:
  val DEFAULT = new Int168(BigInt(0))
end Int168

final class Int168(value: BigInt) extends SolidityInt(168, value):
  def this(value: Long) = this(BigInt(value))
end Int168
