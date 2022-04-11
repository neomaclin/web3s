package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int48:
  val DEFAULT = new Int48(BigInt(0))
end Int48

final class Int48(value: BigInt) extends SolidityInt(48, value):
  def this(value: Long) = this(BigInt(value))
end Int48
