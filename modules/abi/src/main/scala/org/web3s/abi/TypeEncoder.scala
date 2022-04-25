package org.web3s.abi

import izumi.reflect.Tag
import org.bouncycastle.util
import org.web3s.abi.datatypes.*
import org.web3s.abi.datatypes.SolidityType.MAX_BIT_LENGTH
import org.web3s.utils.Numeric

import scala.collection.mutable

object TypeEncoder:

  def encode[T <: SolidityType[_] : Tag](using Encodable[T])(value: T) =
    summon[Encodable[T]].encode(value)
//  def isDynamic[T:Tag]: Boolean = Tag[T].tag.toString match {
//    case "DynamicBytes" | "Utf8String" | "DynamicArray" => true
//    case "StaticArray" | "DynamicStruct" => true
//    case _ => false
//  }
    //  def encodeAddress(address: Address): String = encodeNumeric(address.toUint)
//
////  def encodeNumeric(numericType: NumericType) = {
////    val rawValue = toByteArray(numericType)
////    val paddingValue = getPaddingValue(numericType)
////    val paddedRawValue = new Array[Byte](MAX_BYTE_LENGTH)
////    if (paddingValue != 0) for (i <- 0 until paddedRawValue.length) {
////      paddedRawValue(i) = paddingValue
////    }
////    System.arraycopy(rawValue, 0, paddedRawValue, MAX_BYTE_LENGTH - rawValue.length, rawValue.length)
////    Numeric.toHexStringNoPrefix(paddedRawValue)
////  }
//
//  def encodeBool(value: Bool): String =
//    val rawValue = Array.fill[Byte](MAX_BYTE_LENGTH)(0.toByte)
//    if value.value then rawValue(rawValue.length - 1) = 1 else rawValue(rawValue.length - 1) = 0
//    Numeric.toHexStringNoPrefix(rawValue)
//  end encodeBool
//
//  def encodeBytes(bytesType: BytesType): String =
//    val value = bytesType.value
//    val length = value.length
//    val mod = length % MAX_BYTE_LENGTH
//    val dest = if mod != 0 then
//      val temp = new Array[Byte](length + (MAX_BYTE_LENGTH - mod))
//      Array.copy(value, 0, temp, length)
//      temp
//    else value
//    Numeric.toHexStringNoPrefix(dest)
//  end encodeBytes
//
//  def encodeDynamicBytes(dynamicBytes: DynamicBytes): String =
//    encode(new SolidityUint(BigInt(dynamicBytes.value.length))) ++ encodeBytes(dynamicBytes)
//  end encodeDynamicBytes
//
//  def encodeString(string: Utf8String): String = encodeDynamicBytes(new DynamicBytes(string.value))
//
//  def encodeDynamicStruct(value: DynamicStruct):String = encodeDynamicStructValues(value)


//   def encodeDynamicArray[T <: SolidityType[_]:Tag](value: DynamicArray[T]): String =
//     encode(new Uint(BigInteger.valueOf(value.value.size))) ++ encodeArrayValuesOffsets(value) ++ encodeArrayValues(value)
//   end encodeDynamicArray

end TypeEncoder

