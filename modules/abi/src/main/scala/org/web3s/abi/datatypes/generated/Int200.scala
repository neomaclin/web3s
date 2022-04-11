package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int200:
  val DEFAULT = new Int200(BigInt(0))
end Int200

final class Int200(value: BigInt) extends SolidityInt(200, value):
  def this(value: Long) = this(BigInt(value))
end Int200
