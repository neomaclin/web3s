package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int144:
  val DEFAULT = Int144(BigInt(0))

final case class Int144(override val value: BigInt) extends EthInt(144, value):
  def this(value: Long) = this(BigInt(value))
