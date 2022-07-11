package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int144:
  val DEFAULT = new Int144(BigInt(0))
end Int144

final class Int144(value: BigInt) extends SolidityInt(144, value):
  def this(value: Long) = this(BigInt(value))
end Int144
