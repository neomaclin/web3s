package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes31:
  val DEFAULT = new Bytes31(new Array[Byte](31))
end Bytes31

final class Bytes31(override val value: Array[Byte]) extends Bytes(31, value)
