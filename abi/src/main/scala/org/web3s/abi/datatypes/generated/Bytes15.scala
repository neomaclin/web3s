package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes15:
  val DEFAULT = Bytes15(new Array[Byte](15))

final class Bytes15(override val value: Array[Byte]) extends Bytes(15, value)
