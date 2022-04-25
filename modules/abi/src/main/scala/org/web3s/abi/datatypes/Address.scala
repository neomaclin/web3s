package org.web3s.abi.datatypes


import org.web3s.abi.Encodable
import org.web3s.abi.datatypes.*
import org.web3s.abi.datatypes.SolidityType.MAX_BYTE_LENGTH
import org.web3s.utils.Numeric

/**
 * Address type, which by default is equivalent to uint160 which follows the Ethereum specification.
 */
object Address:

  given Encodable[Address] = Address.encode(_)

  val TYPE_NAME = "address"
  val DEFAULT_LENGTH = 160
  val DEFAULT = new Address(BigInt(0))

//  def decode(rawInput: String, offset: Int): Bool =
//    val input = rawInput.substring(offset, offset + Bool.MAX_BYTE_LENGTH_FOR_HEX_STRING)
//    val numericValue = Numeric.toBigInt(input)
//    new Bool(numericValue == BigInt(1))
//  end decode

  def encode(value: Address): String = NumericType.encode(value.toUInt)
  
end Address


class Address(_value: SolidityUInt) extends SolidityType[String] :

  def this(bitSize: Int, value: BigInt) = this(new SolidityUInt(bitSize, value))

  def this(value: BigInt) = this(Address.DEFAULT_LENGTH, value)

  def this(bitSize: Int, hexValue: String) = this(bitSize, Numeric.toBigInt(hexValue))

  def this(hexValue: String) = this(Address.DEFAULT_LENGTH, hexValue)

  def getTypeAsString: String = Address.TYPE_NAME
  
  def toUInt: SolidityUInt = _value

  override def value: String = toString
  
  override def toString: String = Numeric.toHexStringWithPrefixZeroPadded(_value.value, _value.bitSize >> 2)

  override def equals(o: Any): Boolean = o match
    case other: Address => other.value == value
    case _ => false

  override def hashCode: Int = if (value != null) value.hashCode else 0
  
end Address
