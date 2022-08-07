package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt96:
  val DEFAULT = UInt96(BigInt(0))

final class UInt96(value: BigInt) extends EthUInt(96, value):
  def this(value: Long) = this(BigInt(value))
