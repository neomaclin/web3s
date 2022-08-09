package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt56:
  val DEFAULT = UInt56(BigInt(0))

final case class UInt56(override val value: BigInt) extends EthUInt(56, value):
  def this(value: Long) = this(BigInt(value))
