package org.web3s.abi.datatypes


import org.web3s.abi.datatypes.EthType.MAX_BYTE_LENGTH
import org.web3s.abi.codec.{Encodable, TypeEncoder}
import org.web3s.utils.Numeric
import izumi.reflect.Tag
import scala.reflect.ClassTag
class StaticArray[T <: EthType[_] : Tag: ClassTag](expectedSize: Int, values: Seq[T]) extends EthArray[T]:
  def this(expectedSize: Int, value:T, xs: T*) = this(expectedSize, value +: xs)
  def this(expectedSize: Int) = this(expectedSize, Seq.empty)
  require(values.length <= EthArray.MAX_SIZE_OF_STATIC_ARRAY, "Static arrays with a length greater than " + EthArray.MAX_SIZE_OF_STATIC_ARRAY + " are not supported.")
  require(values.length == expectedSize, "Expected array of type [" + getClass.getSimpleName + "] to have [" + expectedSize + "] elements.")
  override def value: Array[T] = values.toArray
