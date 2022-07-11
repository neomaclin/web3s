package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes20:
  val DEFAULT = new Bytes20(new Array[Byte](20))
end Bytes20

final class Bytes20(override val value: Array[Byte]) extends Bytes(20, value)
