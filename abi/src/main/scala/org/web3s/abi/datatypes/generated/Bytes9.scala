package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes9:
  val DEFAULT = Bytes9(new Array[Byte](9))

final case class Bytes9(override val value: Array[Byte]) extends Bytes(9, value)
