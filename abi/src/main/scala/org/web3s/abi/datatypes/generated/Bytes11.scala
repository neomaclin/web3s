package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes11:
  val DEFAULT = Bytes11(new Array[Byte](11))

final case class Bytes11(override val value: Array[Byte]) extends Bytes(11, value)
