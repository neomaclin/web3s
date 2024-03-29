package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt184:
  val DEFAULT = UInt184(BigInt(0))

final case class UInt184(override val value: BigInt) extends EthUInt(184, value):
  def this(value: Long) = this(BigInt(value))
