package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes23:
  val DEFAULT = new Bytes23(new Array[Byte](23))
end Bytes23

final class Bytes23(override val value: Array[Byte]) extends Bytes(23, value)
