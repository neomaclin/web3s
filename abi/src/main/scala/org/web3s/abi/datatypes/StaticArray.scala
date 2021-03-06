package org.web3s.abi.datatypes

import izumi.reflect.Tag
import org.web3s.abi.datatypes.SolidityType.MAX_BYTE_LENGTH
import org.web3s.abi.{Encodable, TypeEncoder}
import org.web3s.utils.Numeric

object StaticArray:
  
  def encode[T <: SolidityType[_] : Tag : Encodable](value: DynamicStruct[T]): String =
    DynamicArray.encodeStructsArraysOffsets(value) ++ value.values.map(TypeEncoder.encode[T](_)).mkString
  
  
  def encode[T <: SolidityType[_] : Tag : Encodable](value: StaticArray[T]): String =
    value.values.map(TypeEncoder.encode[T](_)).mkString

  def encodePacked[T <: SolidityType[_] : Tag : Encodable](value: StaticArray[T]): String =
      value.values.map(TypeEncoder.encode[T](_)).mkString

end StaticArray

abstract class StaticArray[+T <: SolidityType[_] : Tag](expectedSize: Int,
                                                       override val values: List[T]
                                                      ) extends SolidityArray[T](values) :

  require(values.size <= SolidityArray.MAX_SIZE_OF_STATIC_ARRAY, "Static arrays with a length greater than " + SolidityArray.MAX_SIZE_OF_STATIC_ARRAY + " are not supported.")

  require(values.size == expectedSize, "Expected array of type [" + getClass.getSimpleName + "] to have [" + expectedSize + "] elements.")

  def this(expectedSize: Int, value: T*) = this(expectedSize, List(value *))

end StaticArray
