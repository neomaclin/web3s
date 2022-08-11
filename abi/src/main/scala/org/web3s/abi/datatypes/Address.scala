package org.web3s.abi.datatypes

import org.web3s.abi.codec.{Decodable, Encodable}
import org.web3s.abi.datatypes.*
import org.web3s.abi.datatypes.EthType.MAX_BYTE_LENGTH
//import org.web3s.abi.datatypes.generated.UEthInt160
import org.web3s.utils.Numeric

/**
 * Address type, which by default is equivalent to uint160 which follows the Ethereum specification.
 */
object Address:
  inline val TYPE_NAME = "address"
  inline val DEFAULT_LENGTH = 160
  val DEFAULT = Address(BigInt(0))

final class Address(_value: EthUInt) extends EthType[String] :

  def this(bitSize: Int, value: BigInt) = this(EthUInt(bitSize, value))

  def this(value: BigInt) = this(Address.DEFAULT_LENGTH, value)

  def this(bitSize: Int, hexValue: String) = this(bitSize, Numeric.toBigInt(hexValue))

  def this(hexValue: String) = this(Address.DEFAULT_LENGTH, hexValue)

  def toUInt: EthUInt = _value

  override def value: String = toString
  
  override def toString: String = Numeric.toHexStringWithPrefixZeroPadded(_value.value, _value.bitSize >> 2)

  override def equals(o: Any): Boolean = o match
    case other: Address => other.value == value
    case _ => false

  override def hashCode: Int = if (value.nonEmpty) value.hashCode else 0
  