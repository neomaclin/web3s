package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int128:
  val DEFAULT = Int128(BigInt(0))

final case class Int128(override val value: BigInt) extends EthInt(128, value):
  def this(value: Long) = this(BigInt(value))
