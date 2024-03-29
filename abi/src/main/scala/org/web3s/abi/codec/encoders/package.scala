package org.web3s.abi.codec

import org.web3s.utils.Numeric

import org.web3s.abi.datatypes.*
import org.web3s.abi.datatypes.EthType.*
import org.web3s.abi.datatypes.primitive.*
import org.web3s.abi.codec.{Encodable, TypeEncoder}

import scala.reflect.Typeable

package encoders:

  import izumi.reflect.Tag
  import izumi.reflect.macrortti.LightTypeTagUnpacker

  private def encodeBytesType(bytesType: BytesType): String =
    val value = bytesType.value
    val length = value.length
    val mod = length % MAX_BYTE_LENGTH
    val dest =
      if mod != 0 then
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

  given encodeStaticArrayFor[T <: EthType[_] : Tag : Encodable, A <: StaticArray[T]]: Encodable[A] with
    override def encode(value: A): String =
      value.value.map(TypeEncoder.encode[T](_)).mkString

    override def encodePacked(value: A): String = encode(value)

  given encodeDynamicArrayFor[T <: EthType[_] : Tag : Encodable: Typeable]: Encodable[DynamicArray[T]] = new Encodable[DynamicArray[T]]:
    private def encodeArrayValuesOffsets(value: DynamicArray[T], typeString: String): String =
      if value.value.isEmpty then ""
      else value.value.view
        .init.foldLeft(List(value.value.length * MAX_BYTE_LENGTH)) { (list, item) =>
        val bytesLength = item match
          case str: EthUtf8String => str.value.length
          case bytes: DynamicBytes => bytes.value.length
          case _ => 0
        val numberOfWords = (bytesLength + MAX_BYTE_LENGTH - 1) / MAX_BYTE_LENGTH
        val totalBytesLength = numberOfWords * MAX_BYTE_LENGTH
        (totalBytesLength + MAX_BYTE_LENGTH + list.head) :: list
      }.reverse.map(offset => Numeric.toHexStringNoPrefix(Numeric.toBytesPadded(BigInt(offset), MAX_BYTE_LENGTH))).mkString

    private def encodeStructsArraysOffsets(value: DynamicArray[T]): String =
      if value.value.isEmpty then ""
      else value.value.view
        .init.map(TypeEncoder.encode[T](_))
        .foldLeft(List(value.value.length * MAX_BYTE_LENGTH)) { (list, item) => (item.length / 2 + list.head) :: list }
        .reverse
        .map(offset => Numeric.toHexStringNoPrefix(Numeric.toBytesPadded(BigInt(offset), MAX_BYTE_LENGTH))).mkString

    override def encode(value: DynamicArray[T]): String =
      val encodedLength = TypeEncoder.encode[EthUInt](EthUInt(BigInt(value.value.size)))
      val chain = LightTypeTagUnpacker(Tag[T].tag).inheritance
      val valuesOffsets = Tag[T].tag.toString match
        case "EthUtf8String" | "DynamicBytes" => encodeArrayValuesOffsets(value, Tag[T].tag.toString)
        case _ =>
          if chain.values.exists(_.map(_.ref.name).exists(_.endsWith("DynamicStruct"))) then encodeStructsArraysOffsets(value)
          else ""
      val encodedValues = value.value.map(TypeEncoder.encode[T](_)).mkString
      encodedLength ++ valuesOffsets ++ encodedValues

    override def encodePacked(value: DynamicArray[T]): String = encode(value).substring(64)
  

  given encodeEthInt[T <: EthInt : Tag]: Encodable[T] with
    override def encode(value: T): String = encodeNumericType[EthInt](value)

    override def encodePacked(value: T): String = encode(value).substring(64 - value.bitSize / 4, 64)

  given encodeEthUInt[T <: EthUInt : Tag]: Encodable[T] with
    override def encode(value: T): String = encodeNumericType[EthUInt](value)

    override def encodePacked(value: T): String = encode(value).substring(64 - value.bitSize / 4, 64)

  given encodeBytes[T <: Bytes : Tag]: Encodable[T] with
    override def encode(value: T): String = encodeBytesType(value)

    override def encodePacked(value: T): String = encode(value).substring(0, value.value.length * 2)

  given encodePrimitive[T <: AnyVal : Typeable, P <: PrimitiveType[T]]: Encodable[P] with
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

  given Encodable[EmptyStruct.type] with
    override def encode(value: EmptyStruct.type): String = ""
    override def encodePacked(value: EmptyStruct.type): String = ""
