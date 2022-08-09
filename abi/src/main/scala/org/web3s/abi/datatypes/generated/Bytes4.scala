package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes4:
  val DEFAULT = Bytes4(new Array[Byte](4))

final case class Bytes4(override val value: Array[Byte]) extends Bytes(4, value)
