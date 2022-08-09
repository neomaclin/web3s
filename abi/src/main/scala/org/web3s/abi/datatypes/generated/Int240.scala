package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int240:
  val DEFAULT = Int240(BigInt(0))

final case class Int240(override val value: BigInt) extends EthInt(240, value):
  def this(value: Long) = this(BigInt(value))
