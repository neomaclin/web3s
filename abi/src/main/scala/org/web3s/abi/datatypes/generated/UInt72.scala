package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt72:
  val DEFAULT = UInt72(BigInt(0))

final case class UInt72(override val value: BigInt) extends EthUInt(72, value):
  def this(value: Long) = this(BigInt(value))
