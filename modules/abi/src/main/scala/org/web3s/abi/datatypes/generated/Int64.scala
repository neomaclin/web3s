package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int64:
  val DEFAULT = new Int64(BigInt(0))
end Int64

final class Int64(value: BigInt) extends SolidityInt(64, value):
  def this(value: Long) = this(BigInt(value))
end Int64
