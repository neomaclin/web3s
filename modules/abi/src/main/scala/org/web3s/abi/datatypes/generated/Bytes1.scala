package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes1:
  val DEFAULT = new Bytes1(new Array[Byte](1))
end Bytes1

final class Bytes1(override val value: Array[Byte]) extends Bytes(1, value)
