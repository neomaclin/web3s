package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt24:
  val DEFAULT = UInt24(BigInt(0))

final case class UInt24(override val value: BigInt) extends EthUInt(24, value):
  def this(value: Long) = this(BigInt(value))
