package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt208:
  val DEFAULT = UInt208(BigInt(0))

final class UInt208(value: BigInt) extends EthUInt(208, value):
  def this(value: Long) = this(BigInt(value))
