package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int56:
  val DEFAULT = Int56(BigInt(0))

final case class Int56(override val value: BigInt) extends EthInt(56, value):
  def this(value: Long) = this(BigInt(value))
