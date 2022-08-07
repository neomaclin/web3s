package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt24:
  val DEFAULT = UInt24(BigInt(0))

final class UInt24(value: BigInt) extends EthUInt(24, value):
  def this(value: Long) = this(BigInt(value))
