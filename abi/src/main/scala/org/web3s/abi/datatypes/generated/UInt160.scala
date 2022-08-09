package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt160:
  val DEFAULT = UInt160(BigInt(0))

final case class UInt160(override val value: BigInt) extends EthUInt(160, value):
  def this(value: Long) = this(BigInt(value))
