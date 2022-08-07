package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int96:
  val DEFAULT = Int96(BigInt(0))

final class Int96(value: BigInt) extends EthInt(96, value):
  def this(value: Long) = this(BigInt(value))
