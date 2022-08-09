package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt152:
  val DEFAULT = UInt152(BigInt(0))

final case class UInt152(override val value: BigInt) extends EthUInt(152, value):
  def this(value: Long) = this(BigInt(value))
