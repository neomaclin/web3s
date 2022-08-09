package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes8:
  val DEFAULT = Bytes8(new Array[Byte](8))

final case class Bytes8(override val value: Array[Byte]) extends Bytes(8, value)
