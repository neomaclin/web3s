package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int224:
  val DEFAULT = Int224(BigInt(0))

final case class Int224(override val value: BigInt) extends EthInt(224, value):
  def this(value: Long) = this(BigInt(value))
