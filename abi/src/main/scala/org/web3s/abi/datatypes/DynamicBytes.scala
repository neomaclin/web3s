package org.web3s.abi.datatypes

object DynamicBytes:
  val DEFAULT = DynamicBytes(Array.empty[Byte])

final case class DynamicBytes(override val value: Array[Byte]) extends BytesType(value, "bytes")