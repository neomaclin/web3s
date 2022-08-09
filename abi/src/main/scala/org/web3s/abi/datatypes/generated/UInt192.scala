package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt192:
  val DEFAULT = UInt192(BigInt(0))

final case class UInt192(override val value: BigInt) extends EthUInt(192, value):
  def this(value: Long) = this(BigInt(value))
