package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int152:
  val DEFAULT = Int152(BigInt(0))

final class Int152(value: BigInt) extends EthInt(152, value):
  def this(value: Long) = this(BigInt(value))
