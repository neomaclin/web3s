package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int160:
  val DEFAULT = new Int160(BigInt(0))
end Int160

final class Int160(value: BigInt) extends SolidityInt(160, value):
  def this(value: Long) = this(BigInt(value))
end Int160
