package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt8:
  val DEFAULT = UInt8(BigInt(0))

final class UInt8(value: BigInt) extends EthUInt(8, value):
  def this(value: Long) = this(BigInt(value))
