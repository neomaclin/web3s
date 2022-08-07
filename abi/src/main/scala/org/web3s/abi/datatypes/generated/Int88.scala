package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int88:
  val DEFAULT = Int88(BigInt(0))

final class Int88(value: BigInt) extends EthInt(88, value):
  def this(value: Long) = this(BigInt(value))
