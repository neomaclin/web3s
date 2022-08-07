package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int72:
  val DEFAULT = Int72(BigInt(0))

final class Int72(value: BigInt) extends EthInt(72, value):
  def this(value: Long) = this(BigInt(value))
