package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt120:
  val DEFAULT = UInt120(BigInt(0))

final class UInt120(value: BigInt) extends EthUInt(120, value):
  def this(value: Long) = this(BigInt(value))
