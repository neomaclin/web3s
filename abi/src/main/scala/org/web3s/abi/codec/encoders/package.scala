package org.web3s.abi.codec

import org.web3s.utils.Numeric

import org.web3s.abi.datatypes.*
import org.web3s.abi.datatypes.EthType.*
import org.web3s.abi.datatypes.primitive.*
import org.web3s.abi.codec.{Encodable, TypeEncoder}

import scala.reflect.Typeable

package encoders:

  import izumi.reflect.Tag

  private def encodeBytesType(bytesType: BytesType): String =
    val value = bytesType.value
    val length = value.length
    val mod = length % MAX_BYTE_LENGTH
    val dest = if mod != 0 then
      val temp = new Array[Byte](length + (MAX_BYTE_LENGTH - mod))
      Array.copy(value, 0, temp, 0, length)
      temp
    else value
    Numeric.toHexStringNoPrefix(dest)

  private def encodeNumericType[T <: EthNumericType : Typeable](numericType: T): String =
    def toByteArray(numericType: T): Array[Byte] =
      numericType match
        case i: EthUInt =>
          if i.value.bitLength == MAX_BIT_LENGTH then i.value.toByteArray.tail else i.value.toByteArray
        case u: UFixed =>
          if u.value.bitLength == MAX_BIT_LENGTH then u.value.toByteArray.tail else u.value.toByteArray
        case _ =>
          numericType.value.toByteArray
    end toByteArray

    def paddingValue(numericType: T): Byte = if numericType.value.signum == -1 then 0xff.toByte else 0

    val rawValue = toByteArray(numericType)
    val value = paddingValue(numericType)
    val paddedRawValue = Array.fill[Byte](MAX_BYTE_LENGTH)(value)

    System.arraycopy(rawValue, 0, paddedRawValue, MAX_BYTE_LENGTH - rawValue.length, rawValue.length)
    Numeric.toHexStringNoPrefix(paddedRawValue)

  given Encodable[Fixed] with
    override def encode(value: Fixed): String = encodeNumericType[Fixed](value)

    override def encodePacked(value: Fixed): String = encode(value)

  given Encodable[UFixed] with
    override def encode(value: UFixed): String = encodeNumericType[UFixed](value)

    override def encodePacked(value: UFixed): String = encode(value)

  given Encodable[Address] with
    override def encode(value: Address): String = encodeNumericType[EthUInt](value.toUInt)

    override def encodePacked(value: Address): String = encode(value).substring(64 - value.toUInt.bitSize / 4, 64)

  given Encodable[Bool] with
    override def encode(value: Bool): String =
      val rawValue = Array.fill[Byte](MAX_BYTE_LENGTH)(0.toByte)
      if value.value then rawValue(rawValue.length - 1) = 1 else rawValue(rawValue.length - 1) = 0
      Numeric.toHexStringNoPrefix(rawValue)

    override def encodePacked(value: Bool): String = encode(value).substring(62, 64)


  given Encodable[DynamicBytes] with
    override def encode(value: DynamicBytes): String =
      encodeNumericType(EthUInt(BigInt(value.value.length))) ++ encodeBytesType(value)

    override def encodePacked(value: DynamicBytes): String = encode(value).substring(64)

  given Encodable[EthUtf8String] with
    override def encode(value: EthUtf8String): String =
      TypeEncoder.encode[DynamicBytes](DynamicBytes(value.value.getBytes))

    override def encodePacked(value: EthUtf8String): String =
      encode(value).substring(64, 64 + value.value.getBytes.length * 2)

  given encodeStaticArrayFor[T <: EthType[_] : Tag : Encodable,A <: StaticArray[T]]: Encodable[A] with
    override def encode(value: A): String =
      value.value.map(TypeEncoder.encode[T](_)).mkString

    override def encodePacked(value: A): String = encode(value)

  given encodeEthInt[T <: EthInt : Tag]: Encodable[T] with
    override def encode(value: T): String = encodeNumericType[EthInt](value)

    override def encodePacked(value: T): String = encode(value).substring(64 - value.bitSize / 4, 64)

  given encodeEthUInt[T <: EthUInt : Tag]: Encodable[T] with
    override def encode(value: T): String = encodeNumericType[EthUInt](value)

    override def encodePacked(value: T): String = encode(value).substring(64 - value.bitSize / 4, 64)

  given encodeBytes[T <: Bytes : Tag]: Encodable[T] with
    override def encode(value: T): String = encodeBytesType(value)

    override def encodePacked(value: T): String = encode(value).substring(0, value.value.length * 2)

  given encodePrimitive[P <: PrimitiveType[_]]: Encodable[P] with
    override def encode(value: P): String = value.value match
      case x: Byte => TypeEncoder.encode(x.asEthType)
      case x: Short => TypeEncoder.encode(x.asEthType)
      case x: Int => TypeEncoder.encode(x.asEthType)
      case x: Long => TypeEncoder.encode(x.asEthType)
      case x: Char => TypeEncoder.encode(x.asEthType)
      case x: Double => throw new UnsupportedOperationException("Type cannot be encoded: Double")
      case x: Float => throw new UnsupportedOperationException("Type cannot be encoded: Float")

    override def encodePacked(value: P): String = value.value match
      case x: Byte => TypeEncoder.encodePacked(x.asEthType)
      case x: Short => TypeEncoder.encodePacked(x.asEthType)
      case x: Int => TypeEncoder.encodePacked(x.asEthType)
      case x: Long => TypeEncoder.encodePacked(x.asEthType)
      case x: Char => TypeEncoder.encodePacked(x.asEthType)
      case x: Double => throw new UnsupportedOperationException("Type cannot be encoded: Double")
      case x: Float => throw new UnsupportedOperationException("Type cannot be encoded: Float")


