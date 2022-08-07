
package org.web3s.abi.datatypes

class Bytes(val byteSize: Int,
            override val value: Array[Byte]) extends BytesType(value, "bytes" + value.length) :
  require(isValid(byteSize), "Input byte array must be in range 0 < M <= 32 and length must match type")

  private def isValid(byteSize: Int) =
    val length = value.length
    length > 0 && length <= 32 && length == byteSize
