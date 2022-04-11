package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes9:
  val DEFAULT = new Bytes9(new Array[Byte](9))
end Bytes9

final class Bytes9(override val value: Array[Byte]) extends Bytes(9, value)
