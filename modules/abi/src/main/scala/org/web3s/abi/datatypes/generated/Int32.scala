package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int32:
  val DEFAULT = new Int32(BigInt(0))
end Int32

final class Int32(value: BigInt) extends SolidityInt(32, value):
  def this(value: Long) = this(BigInt(value))
end Int32
