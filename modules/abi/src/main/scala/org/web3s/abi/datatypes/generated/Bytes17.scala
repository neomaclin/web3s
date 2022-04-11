package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes17:
  val DEFAULT = new Bytes17(new Array[Byte](17))
end Bytes17

final class Bytes17(override val value: Array[Byte]) extends Bytes(17, value)
