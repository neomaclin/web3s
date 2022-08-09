package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt176:
  val DEFAULT = UInt176(BigInt(0))

final case class UInt176(override val value: BigInt) extends EthUInt(176, value):
  def this(value: Long) = this(BigInt(value))
