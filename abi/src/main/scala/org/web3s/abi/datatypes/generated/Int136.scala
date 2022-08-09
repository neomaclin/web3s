package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int136:
  val DEFAULT = Int136(BigInt(0))

final case class Int136(override val value: BigInt) extends EthInt(136, value):
  def this(value: Long) = this(BigInt(value))
