package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes2:
  val DEFAULT = Bytes2(new Array[Byte](2))

final class Bytes2(override val value: Array[Byte]) extends Bytes(2, value)
