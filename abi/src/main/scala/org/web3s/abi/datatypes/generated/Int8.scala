package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int8:
  val DEFAULT = new Int8(BigInt(0))
end Int8

final class Int8(value: BigInt) extends SolidityInt(8, value):
  def this(value: Long) = this(BigInt(value))
end Int8
