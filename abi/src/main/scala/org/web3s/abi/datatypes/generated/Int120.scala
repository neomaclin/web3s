package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int120:
  val DEFAULT = Int120(BigInt(0))

final case class Int120(override val value: BigInt) extends EthInt(120, value):
  def this(value: Long) = this(BigInt(value))
