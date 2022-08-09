package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt104:
  val DEFAULT = UInt104(BigInt(0))

final case class UInt104(override val value: BigInt) extends EthUInt(104, value):
  def this(value: Long) = this(BigInt(value))
