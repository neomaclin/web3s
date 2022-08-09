package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes3:
  val DEFAULT = Bytes3(new Array[Byte](3))

final case class Bytes3(override val value: Array[Byte]) extends Bytes(3, value)
