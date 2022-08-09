package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int232:
  val DEFAULT = Int232(BigInt(0))

final case class Int232(override val value: BigInt) extends EthInt(232, value):
  def this(value: Long) = this(BigInt(value))
