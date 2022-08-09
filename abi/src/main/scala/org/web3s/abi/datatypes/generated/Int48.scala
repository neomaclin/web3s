package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int48:
  val DEFAULT = Int48(BigInt(0))

final case class Int48(override val value: BigInt) extends EthInt(48, value):
  def this(value: Long) = this(BigInt(value))
