package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt88:
  val DEFAULT = UInt88(BigInt(0))

final case class UInt88(override val value: BigInt) extends EthUInt(88, value):
  def this(value: Long) = this(BigInt(value))
