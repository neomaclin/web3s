package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.SolidityInt

object Int216:
  val DEFAULT = new Int216(BigInt(0))
end Int216

final class Int216(value: BigInt) extends SolidityInt(216, value):
  def this(value: Long) = this(BigInt(value))
end Int216
