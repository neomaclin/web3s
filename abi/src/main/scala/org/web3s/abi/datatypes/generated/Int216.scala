package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.EthInt

object Int216:
  val DEFAULT = Int216(BigInt(0))

final case class Int216(override val value: BigInt) extends EthInt(216, value):
  def this(value: Long) = this(BigInt(value))
