package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int64:
  val DEFAULT = Int64(BigInt(0))

final case class Int64(override val value: BigInt) extends EthInt(64, value):
  def this(value: Long) = this(BigInt(value))
