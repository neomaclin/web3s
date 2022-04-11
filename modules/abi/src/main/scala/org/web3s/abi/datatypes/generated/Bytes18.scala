package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes18:
  val DEFAULT = new Bytes18(new Array[Byte](18))
end Bytes18

final class Bytes18(override val value: Array[Byte]) extends Bytes(18, value)
