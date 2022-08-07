package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int80:
  val DEFAULT = Int80(BigInt(0))

final class Int80(value: BigInt) extends EthInt(80, value):
  def this(value: Long) = this(BigInt(value))
