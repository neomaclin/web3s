package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt128:
  val DEFAULT = UInt128(BigInt(0))

final class UInt128(value: BigInt) extends EthUInt(128, value):
  def this(value: Long) = this(BigInt(value))
