package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt232:
  val DEFAULT = UInt232(BigInt(0))

final case class UInt232(override val value: BigInt) extends EthUInt(232, value):
  def this(value: Long) = this(BigInt(value))
