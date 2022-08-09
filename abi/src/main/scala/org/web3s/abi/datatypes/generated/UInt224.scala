package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt224:
  val DEFAULT = UInt224(BigInt(0))

final case class UInt224(override val value: BigInt) extends EthUInt(224, value):
  def this(value: Long) = this(BigInt(value))
