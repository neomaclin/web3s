package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt104:
  val DEFAULT = UInt104(BigInt(0))

final class UInt104(value: BigInt) extends EthUInt(104, value):
  def this(value: Long) = this(BigInt(value))
