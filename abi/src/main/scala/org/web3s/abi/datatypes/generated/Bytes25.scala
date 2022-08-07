package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes25:
  val DEFAULT = Bytes25(new Array[Byte](25))

final class Bytes25(override val value: Array[Byte]) extends Bytes(25, value)
