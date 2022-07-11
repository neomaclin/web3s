package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int152:
  val DEFAULT = new Int152(BigInt(0))
end Int152

final class Int152(value: BigInt) extends SolidityInt(152, value):
  def this(value: Long) = this(BigInt(value))
end Int152
