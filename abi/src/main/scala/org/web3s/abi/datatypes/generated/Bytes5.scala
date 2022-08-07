package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes5:
  val DEFAULT = Bytes5(new Array[Byte](5))

final class Bytes5(override val value: Array[Byte]) extends Bytes(5, value)
