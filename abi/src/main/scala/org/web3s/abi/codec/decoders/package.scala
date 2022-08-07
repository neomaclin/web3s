//package org.web3s.abi.codec
//
//
//import org.web3s.abi.datatypes.*
//import org.web3s.abi.EthTypes
//import org.web3s.abi.codec.{ Encodable, TypeEncoder }
//import org.web3s.utils.Numeric
//import izumi.reflect.Tag
//import scala.reflect.Typeable
//import cats.syntax.functor.*
//package decoders:
//
//  import org.web3s.abi.datatypes.EthType.MAX_BYTE_LENGTH
//  import org.web3s.abi.codec.TypeDecoder.MAX_BYTE_LENGTH_FOR_HEX_STRING
//
//  private val uintR = """UInt(\d*)""".r
//  private val intR = """Int(\d*)""".r
//  private val bytesR = "Bytes(\\d+)".r
//
//
//  given Decodable[Bool] with
//    override def decode(data: String, offset: Int)(using tag: Tag[Bool]): Bool =
//      val input = data.substring(offset, offset + Bool.MAX_BYTE_LENGTH_FOR_HEX_STRING)
//      val numericValue = Numeric.toBigInt(input)
//      Bool(numericValue == BigInt(1))
//
//
//  def typeLengthOf[T <: EthNumericType : Tag]: Int =
//    Tag[T].tag.toString match
//      case uintR(size) => size.toInt
//      case intR(size) => size.toInt
//      case _ => EthType.MAX_BIT_LENGTH
//
//  def typeLengthInBytes[T <: EthNumericType : Tag]: Int = typeLengthOf[T] >> 3
//
//
//  given decodeNumeric[T <: EthNumericType : Tag: Typeable]: Decodable[T] with
//    def decode(rawInput: String, offset: Int = 0): T =
//      val input = rawInput.substring(offset, offset + MAX_BYTE_LENGTH_FOR_HEX_STRING)
//      val inputByteArray = Numeric.hexStringToByteArray(input)
//      val typeLengthAsBytes = typeLengthInBytes[T]
//      val resultByteArray = new Array[Byte](typeLengthAsBytes + 1)
//      val valueOffset = EthType.MAX_BYTE_LENGTH - typeLengthAsBytes
//      Array.copy(inputByteArray, valueOffset, resultByteArray, 0, typeLengthAsBytes)
//
//      val numericValue = BigInt(resultByteArray)
//      
//      Tag[T].tag.toString match
//        case uintR(bitSizeStr) => EthUInt(bitSizeStr.toInt, numericValue).asInstanceOf[T]
//        case intR(bitSizeStr) => EthInt(bitSizeStr.toInt, numericValue).asInstanceOf[T]
//        case "Fixed"  => Fixed(numericValue).asInstanceOf[T]
//        case "UFixed" => UFixed(numericValue).asInstanceOf[T]
//        case _ => EthUInt(numericValue).asInstanceOf[T]
//  
//  given decodeNumericBytes[T <: Bytes : Tag]: Decodable[T] with
//      def decode(input: String, offset: Int): T =
//        val bytesR(lengthStr) = Tag[T].tag.toString
//        val length = lengthStr.toInt
//        val hexStringLength = length << 1
//        val value = Numeric.hexStringToByteArray(input.substring(offset, offset + hexStringLength))
//        Bytes(length, value).asInstanceOf[T]
//    
//
