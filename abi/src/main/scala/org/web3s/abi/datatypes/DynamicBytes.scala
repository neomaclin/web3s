package org.web3s.abi.datatypes

object DynamicBytes:
  val TYPE_NAME = "bytes"
  val DEFAULT = new DynamicBytes(Array.empty[Byte])

class DynamicBytes(override val value: Array[Byte]) extends BytesType(value, DynamicBytes.TYPE_NAME)