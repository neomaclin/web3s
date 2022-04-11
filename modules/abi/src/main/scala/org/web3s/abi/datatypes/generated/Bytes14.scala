package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes14:
  val DEFAULT = new Bytes14(new Array[Byte](14))
end Bytes14

final class Bytes14(override val value: Array[Byte]) extends Bytes(14, value)
