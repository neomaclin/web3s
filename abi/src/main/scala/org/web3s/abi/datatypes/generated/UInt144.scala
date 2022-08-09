package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt144:
  val DEFAULT = UInt144(BigInt(0))

final case class UInt144(override val value: BigInt) extends EthUInt(144, value):
  def this(value: Long) = this(BigInt(value))
