package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int232:
  val DEFAULT = new Int232(BigInt(0))
end Int232

final class Int232(value: BigInt) extends SolidityInt(232, value):
  def this(value: Long) = this(BigInt(value))
end Int232
