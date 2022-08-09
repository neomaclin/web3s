package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int184:
  val DEFAULT = Int184(BigInt(0))

final case class Int184(override val value: BigInt) extends EthInt(184, value):
  def this(value: Long) = this(BigInt(value))
