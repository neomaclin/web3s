package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes20:
  val DEFAULT = Bytes20(new Array[Byte](20))

final class Bytes20(override val value: Array[Byte]) extends Bytes(20, value)
