package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int112:
  val DEFAULT = Int112(BigInt(0))

final class Int112(value: BigInt) extends EthInt(112, value):
  def this(value: Long) = this(BigInt(value))
