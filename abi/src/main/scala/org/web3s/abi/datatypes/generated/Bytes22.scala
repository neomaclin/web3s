package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes22:
  val DEFAULT = Bytes22(new Array[Byte](22))

final case class Bytes22(override val value: Array[Byte]) extends Bytes(22, value)
