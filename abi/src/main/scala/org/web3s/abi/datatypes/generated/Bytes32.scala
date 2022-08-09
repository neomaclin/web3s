package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes32:
  val DEFAULT = Bytes32(new Array[Byte](32))

final case class Bytes32(override val value: Array[Byte]) extends Bytes(32, value)
