package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int184:
  val DEFAULT = Int184(BigInt(0))

final class Int184(value: BigInt) extends EthInt(184, value):
  def this(value: Long) = this(BigInt(value))
