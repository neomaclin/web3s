package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt200:
  val DEFAULT = UInt200(BigInt(0))

final case class UInt200(override val value: BigInt) extends EthUInt(200, value):
  def this(value: Long) = this(BigInt(value))
