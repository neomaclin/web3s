package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt216:
  val DEFAULT = UInt216(BigInt(0))

final class UInt216(value: BigInt) extends EthUInt(216, value):
  def this(value: Long) = this(BigInt(value))
