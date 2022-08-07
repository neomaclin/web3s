package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthUInt

object UInt136:
  val DEFAULT = UInt136(BigInt(0))

final class UInt136(value: BigInt) extends EthUInt(136, value):
  def this(value: Long) = this(BigInt(value))
