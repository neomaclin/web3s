package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes8:
  val DEFAULT = new Bytes8(new Array[Byte](8))
end Bytes8

final class Bytes8(override val value: Array[Byte]) extends Bytes(8, value)
