package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int8:
  val DEFAULT = Int8(BigInt(0))

final class Int8(value: BigInt) extends EthInt(8, value):
  def this(value: Long) = this(BigInt(value))
