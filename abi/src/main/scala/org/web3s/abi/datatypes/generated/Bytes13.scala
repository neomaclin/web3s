package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes13:
  val DEFAULT = Bytes13(new Array[Byte](13))

final case class Bytes13(override val value: Array[Byte]) extends Bytes(13, value)
