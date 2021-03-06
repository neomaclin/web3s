package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes16:
  val DEFAULT = new Bytes16(new Array[Byte](16))
end Bytes16

final class Bytes16(override val value: Array[Byte]) extends Bytes(16, value)
