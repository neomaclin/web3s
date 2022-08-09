package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes30:
  val DEFAULT = Bytes30(new Array[Byte](30))

final case class Bytes30(override val value: Array[Byte]) extends Bytes(30, value)
