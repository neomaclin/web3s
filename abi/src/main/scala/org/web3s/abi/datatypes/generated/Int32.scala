package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int32:
  val DEFAULT = Int32(BigInt(0))

final case class Int32(override val value: BigInt) extends EthInt(32, value):
  def this(value: Long) = this(BigInt(value))
