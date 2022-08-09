package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.Bytes

object Bytes10:
  val DEFAULT = Bytes10(new Array[Byte](10))

final case class Bytes10(override val value: Array[Byte]) extends Bytes(10, value)
