package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int248:
  val DEFAULT = Int248(BigInt(0))

final class Int248(value: BigInt) extends EthInt(248, value):
  def this(value: Long) = this(BigInt(value))
