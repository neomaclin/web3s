package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int208:
  val DEFAULT = Int208(BigInt(0))

final class Int208(value: BigInt) extends EthInt(208, value):
  def this(value: Long) = this(BigInt(value))
