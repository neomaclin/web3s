package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt216:
  val DEFAULT = UInt216(BigInt(0))

final case class UInt216(override val value: BigInt) extends EthUInt(216, value):
  def this(value: Long) = this(BigInt(value))
