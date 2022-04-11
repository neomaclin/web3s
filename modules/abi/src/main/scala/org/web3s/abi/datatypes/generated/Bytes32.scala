package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes32:
  val DEFAULT = new Bytes32(new Array[Byte](32))
end Bytes32

final class Bytes32(override val value: Array[Byte]) extends Bytes(32, value)
