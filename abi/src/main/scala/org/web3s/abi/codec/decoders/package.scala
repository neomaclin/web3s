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

  import izumi.reflect.TagK
  import izumi.reflect.macrortti.LightTypeTagUnpacker
  import org.web3s.abi.codec.Decodable
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

  private def decodeNumeric[T <: EthNumericType : Tag](input: String): BigInt =
    val inputByteArray = Numeric.hexStringToByteArray(input)
    val typeLengthAsBytes = typeLengthInBytes[T]
    val resultByteArray = new Array[Byte](typeLengthAsBytes + 1)
    Tag[T].tag.toString match
      case uintR(size) => ()
      case intR(size) => resultByteArray(0) = inputByteArray(0) // take MSB as sign bit
    val valueOffset = EthType.MAX_BYTE_LENGTH - typeLengthAsBytes
    Array.copy(inputByteArray, valueOffset, resultByteArray, 1, typeLengthAsBytes)
    BigInt(resultByteArray)

  def decodeUintAsInt(rawInput: String, offset: Int): Int = {
    val input = rawInput.substring(offset, offset + MAX_BYTE_LENGTH_FOR_HEX_STRING)
    TypeDecoder.decode[UInt64](input, offset).value.intValue
  }

  inline given Decodable[DynamicBytes] with
    override def decode(rawInput: String, offset: Int): DynamicBytes =
      val encodedLength = decodeUintAsInt(rawInput, offset)
      val hexStringEncodedLength = encodedLength << 1
      val valueOffset = offset + MAX_BYTE_LENGTH_FOR_HEX_STRING

      val data = rawInput.substring(valueOffset, valueOffset + hexStringEncodedLength)
      val bytes = Numeric.hexStringToByteArray(data)
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
    (data: String, offset: Int) => DecoderMacro.initiateInt[T](decodeNumeric[T](data.substring(offset)))

  inline given decodeUInt[T <: EthUInt : Tag]: Decodable[T] =
    (data: String, offset: Int) => DecoderMacro.initiateUInt[T](decodeNumeric[T](data.substring(offset)))


  inline given decodeBytes[T <: Bytes : Tag]: Decodable[T] =
    (data: String, offset: Int) =>
      val bytesR(lengthStr) = Tag[T].tag.toString
      val length = lengthStr.toInt
      val hexStringLength = length << 1
      DecoderMacro.initiateBytes[T](Numeric.hexStringToByteArray(data.substring(offset, offset + hexStringLength)))


  private def build[T <: EthType[_] : Tag : Decodable](data: String, offset: Int, length: Int): Seq[T] =
    val (_, value) = Seq.fill(length)(data).foldLeft((offset, List.empty[T])) { case ((currOffset, elements), input) =>
      if isDynamic[T] then
        val hexStringDataOffset = getDataOffset[T](input, currOffset)
        val element = summon[Decodable[T]].decode(input, offset + hexStringDataOffset)
        val nextOffSet = currOffset + MAX_BYTE_LENGTH_FOR_HEX_STRING
        (nextOffSet, element :: elements)
      else
        val element = summon[Decodable[T]].decode(input, currOffset)
        val nextOffSet = currOffset + getSingleElementLength(input, currOffset) * MAX_BYTE_LENGTH_FOR_HEX_STRING
        (nextOffSet, element :: elements)
    }
    value.reverse

  inline given decodeStaticArray[A[V <: EthType[_]] <: StaticArray[V],T <: EthType[_] : Tag: Decodable]: DecodableSeq[A,T] = new DecodableSeq[A,T] {
    override def decode(data: String, offset: Int, length: Int): Seq[T] =
       build[T](data,offset,length)
  }

  def getSingleElementLength[T <: EthType[_] : Tag](input: String, offset: Int): Int =
    if input.length == offset then 0
    else if LightTypeTagUnpacker(Tag[T].tag).inheritance.values
      .exists(_.map(_.ref.name).exists(name => name.endsWith("DynamicBytes") || name.endsWith("Utf8String"))) then (decodeUintAsInt(input, offset) / EthType.MAX_BYTE_LENGTH) + 2
    else 1
  def getDataOffset[T <: EthType[_] : Tag](input: String, offset: Int): Int =
    if LightTypeTagUnpacker(Tag[T].tag).inheritance.values
      .exists(_.map(_.ref.name).exists(name => name.endsWith("DynamicBytes") || name.endsWith("Utf8String") || name.endsWith("DynamicArray"))) then
      decodeUintAsInt(input, offset) << 1
    else offset
  def isDynamic[T: Tag]: Boolean =
    LightTypeTagUnpacker(Tag[T].tag).inheritance.values
      .exists(_.map(_.ref.name).exists(name => name.endsWith("DynamicBytes") || name.endsWith("Utf8String") || name.endsWith("DynamicStruct")))
