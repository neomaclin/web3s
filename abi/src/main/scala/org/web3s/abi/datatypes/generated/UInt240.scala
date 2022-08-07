package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt240:
  val DEFAULT = UInt240(BigInt(0))

final class UInt240(value: BigInt) extends EthUInt(240, value):
  def this(value: Long) = this(BigInt(value))
