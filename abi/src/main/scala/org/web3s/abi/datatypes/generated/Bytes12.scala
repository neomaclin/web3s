package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes12:
  val DEFAULT = Bytes12(new Array[Byte](12))

final case class Bytes12(override val value: Array[Byte]) extends Bytes(12, value)
