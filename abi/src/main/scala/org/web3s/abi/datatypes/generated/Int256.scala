package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int256:
  val DEFAULT = Int256(BigInt(0))

final case class Int256(override val value: BigInt) extends EthInt(256, value):
  def this(value: Long) = this(BigInt(value))
