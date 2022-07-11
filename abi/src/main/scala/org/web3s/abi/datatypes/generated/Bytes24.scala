package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes24:
  val DEFAULT = new Bytes24(new Array[Byte](24))
end Bytes24

final class Bytes24(override val value: Array[Byte]) extends Bytes(24, value)
