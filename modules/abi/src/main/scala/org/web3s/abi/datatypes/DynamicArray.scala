package org.web3s.abi.datatypes

import izumi.reflect.Tag
import izumi.reflect.macrortti.LightTypeTagUnpacker
import org.web3s.abi.{Encodable, TypeEncoder}
import org.web3s.abi.datatypes.SolidityType.MAX_BYTE_LENGTH
import org.web3s.utils.Numeric

object DynamicArray:

  private def encodeArrayValuesOffsets[T <: SolidityType[_] : Tag : Encodable](value: DynamicArray[T], typeString: String): String =
    if value.values.isEmpty then ""
    else value.values.view
      .init.foldLeft(List(value.values.length * MAX_BYTE_LENGTH)) { (list, item) =>
      val bytesLength =
        typeString match
          case "Utf8String" => item.asInstanceOf[Utf8String].value.length
          case "DynamicBytes" => item.asInstanceOf[DynamicBytes].value.length
          case _ => 0
      val numberOfWords = (bytesLength + MAX_BYTE_LENGTH - 1) / MAX_BYTE_LENGTH
      val totalBytesLength = numberOfWords * MAX_BYTE_LENGTH
      (totalBytesLength + MAX_BYTE_LENGTH + list.head) :: list
    }.reverse.map(offset => Numeric.toHexStringNoPrefix(Numeric.toBytesPadded(BigInt(offset), MAX_BYTE_LENGTH))).mkString
  end encodeArrayValuesOffsets

  def encodeStructsArraysOffsets[T <: SolidityType[_] : Tag : Encodable](value: DynamicArray[T]): String =
    if value.values.isEmpty then ""
    else value.values.view
      .init.map(TypeEncoder.encode[T](_))
      .foldLeft(List(value.values.length * MAX_BYTE_LENGTH)) { (list, item) => (item.length / 2 + list.head) :: list }
      .reverse
      .map(offset => Numeric.toHexStringNoPrefix(Numeric.toBytesPadded(BigInt(offset), MAX_BYTE_LENGTH))).mkString

  def encode[T <: SolidityType[_] : Tag : Encodable](value: DynamicArray[T]): String =
    val size = NumericType.encode(new SolidityUInt(BigInt(value.values.size)))
    val chain = LightTypeTagUnpacker(Tag[T].tag).inheritance
    val offset =
      Tag[T].tag.toString match
        case "Utf8String" | "DynamicBytes" => encodeArrayValuesOffsets(value, Tag[T].tag.toString)
        case _ =>
          if chain.values.exists(_.map(_.ref.name).exists(_.endsWith("DynamicStruct"))) then encodeStructsArraysOffsets(value)
          else ""
    val content = value.value.map(TypeEncoder.encode[T](_)).mkString
    size ++ offset ++ content

end DynamicArray


class DynamicArray[T <: SolidityType[_] : Tag](val values: List[T]) extends SolidityArray[T](values) :

  def this(values: T*) = this(List(values *))

  override def bytes32PaddedLength: Int = super.bytes32PaddedLength + SolidityType.MAX_BYTE_LENGTH

  override def getTypeAsString: String = values.map(_.getTypeAsString).mkString("[", ",", "]")


end DynamicArray
