package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt16:
  val DEFAULT = UInt16(BigInt(0))

final case class UInt16(override val value: BigInt) extends EthUInt(16, value):
  def this(value: Long) = this(BigInt(value))
