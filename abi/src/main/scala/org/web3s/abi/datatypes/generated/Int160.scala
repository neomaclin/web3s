package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int160:
  val DEFAULT = Int160(BigInt(0))

final case class Int160(override val value: BigInt) extends EthInt(160, value):
  def this(value: Long) = this(BigInt(value))
