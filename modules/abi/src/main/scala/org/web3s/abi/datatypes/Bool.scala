package org.web3s.abi.datatypes

import org.web3s.abi.Encodable
import org.web3s.abi.datatypes.SolidityType.MAX_BYTE_LENGTH
import org.web3s.utils.Numeric


object Bool:

  given Encodable[Bool] = Bool.encode(_)

  val TYPE_NAME = "bool"
  val DEFAULT = new Bool(false)
  val MAX_BYTE_LENGTH_FOR_HEX_STRING: Int = MAX_BYTE_LENGTH << 1

  def decode(rawInput: String, offset: Int): Bool =
    val input = rawInput.substring(offset, offset + MAX_BYTE_LENGTH_FOR_HEX_STRING)
    val numericValue = Numeric.toBigInt(input)
    new Bool(numericValue == BigInt(1))
  end decode

  def encode(value: Bool): String =
    val rawValue = Array.fill[Byte](MAX_BYTE_LENGTH)(0.toByte)
    if value.value then rawValue(rawValue.length - 1) = 1 else rawValue(rawValue.length - 1) = 0
    Numeric.toHexStringNoPrefix(rawValue)
  end encode

end Bool

class Bool(override val value: Boolean) extends SolidityType[Boolean]:
  
  override def getTypeAsString: String = Bool.TYPE_NAME

  override def equals(o: Any): Boolean = o match 
    case other: Bool =>  value == other.value
    case _ => false
  
  override def hashCode: Int = if (value) 1 else 0
  
end Bool
