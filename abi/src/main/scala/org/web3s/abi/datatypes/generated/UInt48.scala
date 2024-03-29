package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt48:
  val DEFAULT = UInt48(BigInt(0))

final case class UInt48(override val value: BigInt) extends EthUInt(48, value):
  def this(value: Long) = this(BigInt(value))
