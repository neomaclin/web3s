package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt80:
  val DEFAULT = UInt80(BigInt(0))

final case class UInt80(override val value: BigInt) extends EthUInt(80, value):
  def this(value: Long) = this(BigInt(value))
