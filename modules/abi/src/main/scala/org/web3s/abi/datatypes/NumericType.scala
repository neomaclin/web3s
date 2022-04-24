package org.web3s.abi.datatypes

import izumi.reflect.Tag
import org.web3s.abi.datatypes.SolidityType.{MAX_BIT_LENGTH, MAX_BYTE_LENGTH}
import org.web3s.utils.Numeric

object NumericType:

  def encode(numericType: NumericType): String =
    def toByteArray(numericType: NumericType): Array[Byte] =
      numericType match
        case i:SolidityUInt =>
          if i.value.bitLength == MAX_BIT_LENGTH then i.value.toByteArray.tail else i.value.toByteArray
        case u:UFixed =>
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

//   def decode[T:Tag](input: String): NumericType =
//     val inputByteArray= Numeric.hexStringToByteArray(input)
//     val typeLengthAsBytes: Int = getTypeLengthInBytes(`type`)
//   end decode

end NumericType


abstract class NumericType(val `type`: String, override val value: BigInt) extends SolidityType[BigInt] :
  def getTypeAsString: String = `type`

  def bitSize: Int

end NumericType

