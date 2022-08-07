package org.web3s.abi.datatypes

import izumi.reflect.Tag
import org.web3s.abi.datatypes.EthType.MAX_BYTE_LENGTH
import org.web3s.abi.codec.{Encodable, TypeEncoder}
import org.web3s.utils.Numeric

//object StaticArray:

//  def encode[T <: EthType[_] : Tag : Encodable](value: DynamicStruct[T]): String =
//    DynamicArray.encodeStructsArraysOffsets(value) ++ value.values.map(TypeEncoder.encode[T](_)).mkString
//

class StaticArray[T <: EthType[_] : Tag](expectedSize: Int,
                                          override val value: Seq[T]
                                         ) extends EthArray[T](value) :

  require(value.size <= EthArray.MAX_SIZE_OF_STATIC_ARRAY, "Static arrays with a length greater than " + EthArray.MAX_SIZE_OF_STATIC_ARRAY + " are not supported.")

  require(value.size == expectedSize, "Expected array of type [" + getClass.getSimpleName + "] to have [" + expectedSize + "] elements.")
