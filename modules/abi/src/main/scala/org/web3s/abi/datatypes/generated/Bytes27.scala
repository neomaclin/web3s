package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes27:
  val DEFAULT = new Bytes27(new Array[Byte](27))
end Bytes27

final class Bytes27(override val value: Array[Byte]) extends Bytes(27, value)
