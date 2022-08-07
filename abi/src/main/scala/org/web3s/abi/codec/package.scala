//package org.web3s.abi
//
//import org.web3s.abi.datatypes.*
//import org.web3s.abi.datatypes.EthType.*
//import org.web3s.utils.Numeric
//import izumi.reflect.Tag
//import scala.reflect.Typeable
//
//import org.web3s.abi.codec.TypeDecoder.MAX_BYTE_LENGTH_FOR_HEX_STRING
//
//package codec {
//  
////  private[codec] object NumericType:
////   
////    def decode[T <: EthNumericType : Tag](input: String)(constructor: BigInt => T): T =
////      val inputByteArray = Numeric.hexStringToByteArray(input)
////      val typeLengthAsBytes = EthTypes.typeLengthInBytes[T]
////      val resultByteArray = new Array[Byte](typeLengthAsBytes + 1)
////
////      val valueOffset = EthType.MAX_BYTE_LENGTH - typeLengthAsBytes
////      Array.copy(inputByteArray, valueOffset, resultByteArray, 1, typeLengthAsBytes)
////      val numericValue = BigInt(resultByteArray)
////      constructor(numericValue)
//
//  //  given Decodable[Address] with
//  //    override def decode(data: String, offset: Int)(using tag:Tag[Address]): Address = Address.decode(data, offset)
//
//
//
//
//  //  given Decodable[DynamicBytes] with
////    def decode(input: String, offset: Int)(using tag:Tag[DynamicBytes]): DynamicBytes =
////    //  val encodedLength = SolidityUInt.decode[SolidityUInt](input, 0).value.intValue()
////      val hexStringEncodedLength = encodedLength << 1
////      val valueOffset = offset + MAX_BYTE_LENGTH_FOR_HEX_STRING
////      val data = input.substring(valueOffset, valueOffset + hexStringEncodedLength)
////      val bytes = Numeric.hexStringToByteArray(data)
////      new DynamicBytes(bytes)
//
//
//
//
//
//
//  //  def decode(input: String, offset: Int): DynamicBytes =
////    val encodedLength = SolidityUInt.decode[SolidityUInt](input, 0).value.intValue()
////    val hexStringEncodedLength = encodedLength << 1
////
////    val valueOffset = offset + MAX_BYTE_LENGTH_FOR_HEX_STRING
////    val data = input.substring(valueOffset, valueOffset + hexStringEncodedLength)
////    val bytes = Numeric.hexStringToByteArray(data)
////
////    new DynamicBytes(bytes)
////  end decode
//
//
//}
