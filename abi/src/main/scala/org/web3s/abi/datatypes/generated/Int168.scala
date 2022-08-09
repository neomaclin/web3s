package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int168:
  val DEFAULT = Int168(BigInt(0))

final case class Int168(override val value: BigInt) extends EthInt(168, value):
  def this(value: Long) = this(BigInt(value))
