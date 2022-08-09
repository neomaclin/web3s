package org.web3s.abi.codec


import org.web3s.abi.datatypes.*
import org.web3s.abi.datatypes.generated.*
import org.web3s.abi.EthTypes
import org.web3s.abi.codec.{Encodable, TypeEncoder}
import org.web3s.utils.Numeric
import izumi.reflect.Tag

import scala.reflect.Typeable
import cats.syntax.functor.*


package decoders:

  import org.web3s.abi.datatypes.EthType.MAX_BYTE_LENGTH
  import org.web3s.abi.codec.TypeDecoder.MAX_BYTE_LENGTH_FOR_HEX_STRING
  import org.web3s.abi.datatypes.EthNumericType

  import java.nio.charset.StandardCharsets

  def typeLengthOf[T <: EthNumericType : Tag]: Int =
    Tag[T].tag.toString match
      case uintR(size) => size.toInt
      case intR(size) => size.toInt
      case _ => EthType.MAX_BIT_LENGTH

  def typeLengthInBytes[T <: EthNumericType : Tag]: Int = typeLengthOf[T] >> 3

  private val uintR = """UInt(\d*)""".r
  private val intR = """Int(\d*)""".r
  private val bytesR = "Bytes(\\d+)".r

  private def decodeNumeric[T <: EthNumericType : Tag](input: String, offset: Int = 0): BigInt =
    val inputByteArray = Numeric.hexStringToByteArray(input)
    val typeLengthAsBytes = typeLengthInBytes[T]
    val resultByteArray = new Array[Byte](typeLengthAsBytes + 1)
    Tag[T].tag.toString match
      case uintR(size) => ()
      case intR(size) => resultByteArray(0) = inputByteArray(0) // take MSB as sign bit
    val valueOffset = EthType.MAX_BYTE_LENGTH - typeLengthAsBytes
    Array.copy(inputByteArray, valueOffset, resultByteArray, 1, typeLengthAsBytes)
    BigInt(resultByteArray)

  inline given Decodable[DynamicBytes] with
    override def decode(rawInput: String, offset: Int): DynamicBytes =
      val input = rawInput.substring(offset, offset + MAX_BYTE_LENGTH_FOR_HEX_STRING)
      val encodedLength = TypeDecoder.decode[UInt64](input, 0).value.intValue
      val hexStringEncodedLength = encodedLength << 1
      val valueOffset: Int = offset + MAX_BYTE_LENGTH_FOR_HEX_STRING

      val data: String = rawInput.substring(valueOffset, valueOffset + hexStringEncodedLength)
      val bytes: Array[Byte] = Numeric.hexStringToByteArray(data)
      DynamicBytes(bytes)


  inline given Decodable[Address] with
    override def decode(data: String, offset: Int): Address =
      Address(TypeDecoder.decode[UInt160](data, offset))

  inline given Decodable[EthUtf8String] with
    override def decode(data: String, offset: Int): EthUtf8String =
      EthUtf8String(new String(TypeDecoder.decode[DynamicBytes](data, offset).value, StandardCharsets.UTF_8))


  inline given Decodable[Bool] with
    override def decode(data: String, offset: Int): Bool =
      val input = data.substring(offset, offset + MAX_BYTE_LENGTH_FOR_HEX_STRING)
      val numericValue = Numeric.toBigInt(input)
      Bool(numericValue == BigInt(1))

  inline given decodeInt[T <: EthInt : Tag]: Decodable[T] =
    (data: String, offset: Int) => DecoderMacro.initiateInt[T](decodeNumeric[T](data, offset))

  inline given decodeUInt[T <: EthUInt : Tag]: Decodable[T] = {
    (data: String, offset: Int) => DecoderMacro.initiateUInt[T](decodeNumeric[T](data, offset))
  }

  inline given decodeBytes[T <: Bytes : Tag]: Decodable[T] =

    (data: String, offset: Int) =>
      val bytesR(lengthStr) = Tag[T].tag.toString
      val length = lengthStr.toInt
      val hexStringLength = length << 1
      DecoderMacro.initiateBytes[T](Numeric.hexStringToByteArray(data.substring(offset, offset + hexStringLength)))
//    
//
