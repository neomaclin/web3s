
package org.web3s.abi.datatypes

import org.web3s.abi.Encodable
import org.web3s.abi.TypeDecoder.MAX_BYTE_LENGTH_FOR_HEX_STRING
import org.web3s.utils.Numeric

object DynamicBytes:

  given Encodable[DynamicBytes] = new Encodable[DynamicBytes] :
    override def encode(value: DynamicBytes): String = DynamicBytes.encode(value)
    override def encodePacked(value: DynamicBytes): String = DynamicBytes.encode(value).substring(64)

  val TYPE_NAME = "bytes"
  val DEFAULT = new DynamicBytes(Array.empty[Byte])

  def encode(dynamicBytes: DynamicBytes): String =
    NumericType.encode(new SolidityUInt(BigInt(dynamicBytes.value.length))) ++  Bytes.encode(dynamicBytes)
  
  def decode(input: String, offset: Int): DynamicBytes =
    val encodedLength = SolidityUInt.decode[SolidityUInt](input, 0).value.intValue()
    val hexStringEncodedLength = encodedLength << 1

    val valueOffset = offset + MAX_BYTE_LENGTH_FOR_HEX_STRING
    val data = input.substring(valueOffset, valueOffset + hexStringEncodedLength)
    val bytes = Numeric.hexStringToByteArray(data)

    new DynamicBytes(bytes)
  end decode

end DynamicBytes

class DynamicBytes(override val value: Array[Byte]) extends BytesType(value, DynamicBytes.TYPE_NAME)