package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt168:
  val DEFAULT = UInt168(BigInt(0))

final case class UInt168(override val value: BigInt) extends EthUInt(168, value):
  def this(value: Long) = this(BigInt(value))
