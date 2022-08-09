package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt136:
  val DEFAULT = UInt136(BigInt(0))

final case class UInt136(override val value: BigInt) extends EthUInt(136, value):
  def this(value: Long) = this(BigInt(value))
