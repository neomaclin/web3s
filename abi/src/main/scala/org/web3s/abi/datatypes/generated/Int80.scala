package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int80:
  val DEFAULT = new Int80(BigInt(0))
end Int80

final class Int80(value: BigInt) extends SolidityInt(80, value):
  def this(value: Long) = this(BigInt(value))
end Int80
