package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int136:
  val DEFAULT = new Int136(BigInt(0))
end Int136

final class Int136(value: BigInt) extends SolidityInt(136, value):
  def this(value: Long) = this(BigInt(value))
end Int136
