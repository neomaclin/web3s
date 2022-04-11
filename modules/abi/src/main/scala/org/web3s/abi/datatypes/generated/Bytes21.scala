package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes21:
  val DEFAULT = new Bytes21(new Array[Byte](21))
end Bytes21

final class Bytes21(override val value: Array[Byte]) extends Bytes(21, value)
