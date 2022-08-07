package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes23:
  val DEFAULT = Bytes23(new Array[Byte](23))

final class Bytes23(override val value: Array[Byte]) extends Bytes(23, value)
