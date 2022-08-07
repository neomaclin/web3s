package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt64:
  val DEFAULT = UInt64(BigInt(0))

final class UInt64(value: BigInt) extends EthUInt(64, value):
  def this(value: Long) = this(BigInt(value))
