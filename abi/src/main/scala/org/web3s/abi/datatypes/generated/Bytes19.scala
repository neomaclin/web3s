package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes19:
  val DEFAULT = new Bytes19(new Array[Byte](19))
end Bytes19

final class Bytes19(override val value: Array[Byte]) extends Bytes(19, value)
