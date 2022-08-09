package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes19:
  val DEFAULT = Bytes19(new Array[Byte](19))

final case class Bytes19(override val value: Array[Byte]) extends Bytes(19, value)
