package org.web3s.abi.datatypes

import org.web3s.abi.datatypes.EthType.MAX_BIT_LENGTH

object EthFixedPointType:
  val DEFAULT_BIT_LENGTH: Int = MAX_BIT_LENGTH >> 1

  def convert(m: BigInt, n: BigInt): BigInt = convert(DEFAULT_BIT_LENGTH, DEFAULT_BIT_LENGTH, m, n)

  def convert(mBitSize: Int, nBitSize: Int, m: BigInt, n: BigInt): BigInt =
    val shift = (n.bitLength + 3) & ~0x03
    (m << nBitSize) | (n << (nBitSize - shift))

sealed abstract class EthFixedPointType(val typePrefix: String,
                                        mBitSize: Int,
                                        nBitSize: Int,
                                        override val value: BigInt) extends EthNumericType(typePrefix + mBitSize + "x" + nBitSize, value) :
  final val bitSize = mBitSize + nBitSize

  require(valid(mBitSize, nBitSize, value), "Bitsize must be 8 bit aligned, and in range 0 < bitSize <= 256")

  private def isValidBitCount(mBitSize: Int, nBitSize: Int, value: BigInt) = value.bitCount <= mBitSize + nBitSize

  def valid(mBitSize: Int, nBitSize: Int, value: BigInt): Boolean =
    isValidBitSize(mBitSize, nBitSize) && isValidBitCount(mBitSize, nBitSize, value)

  private def isValidBitSize(mBitSize: Int, nBitSize: Int) =
    mBitSize % 8 == 0 && nBitSize % 8 == 0 && bitSize > 0 && bitSize <= EthType.MAX_BIT_LENGTH


object Fixed:
  val DEFAULT = Fixed(BigInt(0))

final class Fixed(val mBitSize: Int,
                  val nBitSize: Int,
                  override val value: BigInt) extends EthFixedPointType(typePrefix = "fixed", mBitSize, nBitSize, value) :
  def this(value: BigInt) = this(EthFixedPointType.DEFAULT_BIT_LENGTH, EthFixedPointType.DEFAULT_BIT_LENGTH, value)

  def this(m: BigInt, n: BigInt) = this(EthFixedPointType.convert(m, n))

  def this(mBitSize: Int, nBitSize: Int, m: BigInt, n: BigInt) = this(EthFixedPointType.convert(mBitSize, nBitSize, m, n))

object UFixed:
  val DEFAULT = UFixed(BigInt(0))

final class UFixed(val mBitSize: Int,
                   val nBitSize: Int,
                   override val value: BigInt) extends EthFixedPointType(typePrefix = "ufixed", mBitSize, nBitSize, value) :
  def this(value: BigInt) = this(EthFixedPointType.DEFAULT_BIT_LENGTH, EthFixedPointType.DEFAULT_BIT_LENGTH, value)

  def this(m: BigInt, n: BigInt) = this(EthFixedPointType.convert(m, n))

  def this(mBitSize: Int, nBitSize: Int, m: BigInt, n: BigInt) = this(EthFixedPointType.convert(mBitSize, nBitSize, m, n))

  override def valid(mBitSize: Int, nBitSize: Int, value: BigInt): Boolean = super.valid(mBitSize, nBitSize, value) && value.signum != -1
