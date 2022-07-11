package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes6:
  val DEFAULT = new Bytes6(new Array[Byte](6))
end Bytes6

final class Bytes6(override val value: Array[Byte]) extends Bytes(6, value)
