
package org.web3s.abi.datatypes

import izumi.reflect.Tag
import org.web3s.abi.{Decodable, Encodable}
import org.web3s.abi.datatypes.SolidityType.MAX_BYTE_LENGTH
import org.web3s.utils.Numeric

object Bytes:
  val TYPE_NAME = "bytes"

  given Encodable[Bytes] = new Encodable[Bytes] :
    override def encode(value: Bytes): String = Bytes.encode(value)
    override def encodePacked(value: Bytes): String = Bytes.encode(value).substring(0, value.value.length * 2)

  //  given Decodable[Bytes] = new Decodable[Bytes] {
  //    override def decode[T <: Bytes : Tag](data: String, offset: Int): T = Bytes.decode[T](data, offset)
  //  }
  def encode(bytesType: BytesType): String =
    val value = bytesType.value
    val length = value.length
    val mod = length % MAX_BYTE_LENGTH
    val dest = if mod != 0 then
      val temp = new Array[Byte](length + (MAX_BYTE_LENGTH - mod))
      Array.copy(value, 0, temp, 0, length)
      temp
    else value
    Numeric.toHexStringNoPrefix(dest)
  end encode

  private val bytesR = "Bytes(\\d+)".r

  def decode[T <: Bytes : Tag](input: String, offset: Int): T =
    val bytesR(lengthStr) = Tag[T].tag.toString
    val length = lengthStr.toInt
    val hexStringLength = length << 1
    val value = Numeric.hexStringToByteArray(input.substring(offset, offset + hexStringLength))
    new Bytes(length, value).asInstanceOf[T]
  end decode

end Bytes

class Bytes(val byteSize: Int,
            override val value: Array[Byte]) extends BytesType(value, Bytes.TYPE_NAME + value.length) :
  require(isValid(byteSize), "Input byte array must be in range 0 < M <= 32 and length must match type")

  private def isValid(byteSize: Int) =
    val length = value.length
    length > 0 && length <= 32 && length == byteSize

end Bytes

