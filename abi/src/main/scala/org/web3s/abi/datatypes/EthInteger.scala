package org.web3s.abi.datatypes

import org.web3s.abi.datatypes.EthType.MAX_BIT_LENGTH


abstract class EthIntType(val typePrefix: String,
                          override val bitSize: Int,
                          override val value: BigInt) extends EthNumericType(typePrefix + bitSize, value) :
  require(valid, "Bit size must be 8 bit aligned, " + "and in range 0 < bitSize <= " + EthType.MAX_BIT_LENGTH)
  private def isValidBitSize(bitSize: Int) = bitSize % 8 == 0 && bitSize > 0 && bitSize <= EthType.MAX_BIT_LENGTH
  private def isValidBitCount(bitSize: Int, value: BigInt) = value.bitLength <= bitSize
  def valid: Boolean = isValidBitSize(bitSize) && isValidBitCount(bitSize, value)

// "int" values should be declared as int256 in computing function selectors


object EthInt:
  val TYPE_NAME = "int"
  val DEFAULT = EthInt(BigInt(0))

open class EthInt(override val bitSize: Int,
             override val value: BigInt) extends EthIntType(EthInt.TYPE_NAME, bitSize, value) :
  def this(value: BigInt) = this(MAX_BIT_LENGTH, value)


object EthUInt:
  val TYPE_NAME = "uint"
  val DEFAULT = EthUInt(BigInt(0))

open class EthUInt(override val bitSize: Int,
              override val value: BigInt) extends EthIntType(EthUInt.TYPE_NAME, bitSize, value):
  def this(value: BigInt) = this(MAX_BIT_LENGTH, value)
  override def valid: Boolean = super.valid && 0 <= value.signum
