package org.web3s.abi.datatypes

import org.web3s.abi.{Decodable, Encodable, SolidityTypes}
import org.web3s.abi.TypeDecoder.MAX_BYTE_LENGTH_FOR_HEX_STRING
import org.web3s.abi.datatypes.SolidityType.MAX_BIT_LENGTH
import org.web3s.utils.Numeric
import izumi.reflect.Tag
import org.web3s.abi.datatypes.generated.*

object SolidityUInt:
  
  val TYPE_NAME = "uint"
  val DEFAULT = new SolidityUInt(BigInt(0))
  private val UnitR = "UInt(\\d)".r

  def decode[T <: SolidityUInt:Tag](rawInput: String, offset: Int = 0): T =
    val input = rawInput.substring(offset, offset + MAX_BYTE_LENGTH_FOR_HEX_STRING)
    val inputByteArray = Numeric.hexStringToByteArray(input)
    val typeLengthAsBytes = SolidityTypes.typeLengthInBytes[T]
    val resultByteArray = new Array[Byte](typeLengthAsBytes + 1)
    val valueOffset = SolidityType.MAX_BYTE_LENGTH - typeLengthAsBytes
    Array.copy(inputByteArray, valueOffset, resultByteArray, 0, typeLengthAsBytes)

    val numericValue = BigInt(resultByteArray)

    Tag[T].tag.toString match
      case UnitR(bitSizeStr) => new SolidityUInt(bitSizeStr.toInt, numericValue).asInstanceOf[T]
      case _ => new SolidityUInt(numericValue).asInstanceOf[T]


  end decode

  given Decodable[SolidityUInt] = new Decodable[SolidityUInt]:
    override def decode(data: String, offset: Int): SolidityUInt = SolidityUInt.decode(data, offset)

  given Encodable[SolidityUInt] = new Encodable[SolidityUInt]:
    override def encode(value: SolidityUInt): String = summon[Encodable[NumericType]].encode(value)
    override def encodePacked(value: SolidityUInt): String = summon[Encodable[NumericType]].encodePacked(value)

end SolidityUInt


class SolidityUInt(override val bitSize: Int,
                   override val value: BigInt) extends IntType(SolidityUInt.TYPE_NAME, bitSize, value):
  
  def this(value: BigInt) = this(MAX_BIT_LENGTH, value)// "int" values should be declared as int256 in computing function selectors
  
  override def valid: Boolean = super.valid && 0 <= value.signum

end SolidityUInt
