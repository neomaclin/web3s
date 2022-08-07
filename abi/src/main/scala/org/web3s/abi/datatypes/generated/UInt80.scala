package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt80:
  val DEFAULT = UInt80(BigInt(0))

final class UInt80(value: BigInt) extends EthUInt(80, value):
  def this(value: Long) = this(BigInt(value))
