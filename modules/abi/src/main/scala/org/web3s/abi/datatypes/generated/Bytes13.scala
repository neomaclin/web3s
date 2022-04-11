package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes13:
  val DEFAULT = new Bytes13(new Array[Byte](13))
end Bytes13

final class Bytes13(override val value: Array[Byte]) extends Bytes(13, value)
