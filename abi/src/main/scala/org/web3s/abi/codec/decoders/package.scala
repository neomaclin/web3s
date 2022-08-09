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

  inline given Decodable[Address] with
    override def decode(data: String, offset: Int): Address =
      Address(TypeDecoder.decode[UInt160](data, offset))


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


//  given decodeNumericBytes[T <: Bytes : Tag]: Decodable[T] with
//      def decode(input: String, offset: Int): T =
//        val bytesR(lengthStr) = Tag[T].tag.toString
//        val length = lengthStr.toInt
//        val hexStringLength = length << 1
//        val value = Numeric.hexStringToByteArray(input.substring(offset, offset + hexStringLength))
//        Bytes(length, value).asInstanceOf[T]
//    
//
