package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int40:
  val DEFAULT = Int40(BigInt(0))

final case class Int40(override val value: BigInt) extends EthInt(40, value):
  def this(value: Long) = this(BigInt(value))
