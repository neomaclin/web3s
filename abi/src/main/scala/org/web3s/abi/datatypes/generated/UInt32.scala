package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt32:
  val DEFAULT = UInt32(BigInt(0))

final case class UInt32(override val value: BigInt) extends EthUInt(32, value):
  def this(value: Long) = this(BigInt(value))
