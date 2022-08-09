package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int192:
  val DEFAULT = Int192(BigInt(0))

final case class Int192(override val value: BigInt) extends EthInt(192, value):
  def this(value: Long) = this(BigInt(value))
