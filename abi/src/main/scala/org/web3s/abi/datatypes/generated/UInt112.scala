package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt112:
  val DEFAULT = UInt112(BigInt(0))

final case class UInt112(override val value: BigInt) extends EthUInt(112, value):
  def this(value: Long) = this(BigInt(value))
