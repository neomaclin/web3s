package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int104:
  val DEFAULT = Int104(BigInt(0))

final class Int104(value: BigInt) extends EthInt(104, value):
  def this(value: Long) = this(BigInt(value))
