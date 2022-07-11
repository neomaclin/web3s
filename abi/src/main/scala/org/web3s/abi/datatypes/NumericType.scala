package org.web3s.abi.datatypes

import izumi.reflect.Tag
import org.web3s.abi.{Encodable, SolidityTypes}
import org.web3s.abi.datatypes.SolidityType.{MAX_BIT_LENGTH, MAX_BYTE_LENGTH}
import org.web3s.utils.Numeric

object NumericType:

  given Encodable[NumericType] = new Encodable[NumericType] :
    override def encode(value: NumericType): String = NumericType.encode(value)

    override def encodePacked(value: NumericType): String = NumericType.encode(value).substring(64 - value.bitSize / 4, 64)

  def encode(numericType: NumericType): String =
    def toByteArray(numericType: NumericType): Array[Byte] =
      numericType match
        case i: SolidityUInt =>
          if i.value.bitLength == MAX_BIT_LENGTH then i.value.toByteArray.tail else i.value.toByteArray
        case u: UFixed =>
          if u.value.bitLength == MAX_BIT_LENGTH then u.value.toByteArray.tail else u.value.toByteArray
        case _ =>
          numericType.value.toByteArray
    end toByteArray

    def paddingValue(numericType: NumericType): Byte = if numericType.value.signum == -1 then 0xff.toByte else 0

    val rawValue = toByteArray(numericType)
    val value = paddingValue(numericType)
    val paddedRawValue = Array.fill[Byte](MAX_BYTE_LENGTH)(value)

    System.arraycopy(rawValue, 0, paddedRawValue, MAX_BYTE_LENGTH - rawValue.length, rawValue.length)
    Numeric.toHexStringNoPrefix(paddedRawValue)
  end encode

  //  def decode[T <: NumericType : Tag](input: String): T =
  //    val inputByteArray = Numeric.hexStringToByteArray(input)
  //    val typeLengthAsBytes = SolidityTypes.typeLengthInBytes[T]
  //    val resultByteArray = new Array[Byte](typeLengthAsBytes + 1)
  //
  //    val valueOffset = SolidityType.MAX_BYTE_LENGTH - typeLengthAsBytes
  //    Array.copy(inputByteArray, valueOffset, resultByteArray, 1, typeLengthAsBytes)
  //    val numericValue = BigInt(resultByteArray)
  //
  //  end decode


end NumericType


abstract class NumericType(val `type`: String, override val value: BigInt) extends SolidityType[BigInt] :
  def getTypeAsString: String = `type`

  def bitSize: Int

end NumericType

