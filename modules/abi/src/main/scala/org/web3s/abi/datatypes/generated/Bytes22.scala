package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes22:
  val DEFAULT = new Bytes22(new Array[Byte](22))
end Bytes22

final class Bytes22(override val value: Array[Byte]) extends Bytes(22, value)
