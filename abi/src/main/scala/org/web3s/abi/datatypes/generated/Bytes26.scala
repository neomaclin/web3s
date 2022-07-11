package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes26:
  val DEFAULT = new Bytes26(new Array[Byte](26))
end Bytes26

final class Bytes26(override val value: Array[Byte]) extends Bytes(26, value)
