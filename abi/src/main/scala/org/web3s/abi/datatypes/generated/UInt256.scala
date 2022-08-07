package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt256:
  val DEFAULT = UInt256(BigInt(0))

final class UInt256(value: BigInt) extends EthUInt(256, value):
  def this(value: Long) = this(BigInt(value))
