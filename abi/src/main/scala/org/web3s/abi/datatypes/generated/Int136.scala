package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int136:
  val DEFAULT = Int136(BigInt(0))

final class Int136(value: BigInt) extends EthInt(136, value):
  def this(value: Long) = this(BigInt(value))
