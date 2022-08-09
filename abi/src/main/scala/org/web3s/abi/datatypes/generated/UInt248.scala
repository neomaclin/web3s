package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt248:
  val DEFAULT = UInt248(BigInt(0))

final case class UInt248(override val value: BigInt) extends EthUInt(248, value):
  def this(value: Long) = this(BigInt(value))
