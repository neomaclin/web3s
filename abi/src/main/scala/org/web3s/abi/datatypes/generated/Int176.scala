package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int176:
  val DEFAULT = Int176(BigInt(0))

final case class Int176(override val value: BigInt) extends EthInt(176, value):
  def this(value: Long) = this(BigInt(value))
