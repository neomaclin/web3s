package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes7:
  val DEFAULT = new Bytes7(new Array[Byte](7))
end Bytes7

final class Bytes7(override val value: Array[Byte]) extends Bytes(7, value)
