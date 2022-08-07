package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int176:
  val DEFAULT = Int176(BigInt(0))

final class Int176(value: BigInt) extends EthInt(176, value):
  def this(value: Long) = this(BigInt(value))
