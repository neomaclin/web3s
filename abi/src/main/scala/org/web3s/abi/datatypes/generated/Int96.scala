package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int96:
  val DEFAULT = new Int96(BigInt(0))
end Int96

final class Int96(value: BigInt) extends SolidityInt(96, value):
  def this(value: Long) = this(BigInt(value))
end Int96
