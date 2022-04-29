package org.web3s.abi.datatypes


import org.web3s.abi.{Decodable, Encodable}
import org.web3s.abi.datatypes.*
import org.web3s.abi.datatypes.SolidityType.MAX_BYTE_LENGTH
import org.web3s.abi.datatypes.generated.UInt160
import org.web3s.utils.Numeric

/**
 * Address type, which by default is equivalent to uint160 which follows the Ethereum specification.
 */
object Address:

  given Encodable[Address] = new Encodable[Address]:
    override def encode(value: Address): String = Address.encode(value)
    override def encodePacked(value: Address): String =
      Address.encode(value).substring(64 - value.toUInt.bitSize / 4, 64)

  given Decodable[Address] = new Decodable[Address]:
    override def decode(data: String, offset: Int):Address = Address.decode(data,offset)

  val TYPE_NAME = "address"
  val DEFAULT_LENGTH = 160
  val DEFAULT = new Address(BigInt(0))

  def decode(rawInput: String, offset: Int): Address = new Address(SolidityUInt.decode[UInt160](rawInput, offset))

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
