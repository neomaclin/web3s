package org.web3s.abi.datatypes

import scala.quoted._

abstract class StaticArray[T <: SolidityType[_] : Type](expectedSize: Int,
                                                        override val value: List[T]
                                                       )(using Quotes) extends SolidityArray[T](value):

  require(value.size <= SolidityArray.MAX_SIZE_OF_STATIC_ARRAY, "Static arrays with a length greater than " + SolidityArray.MAX_SIZE_OF_STATIC_ARRAY + " are not supported.")

  require(value.size == expectedSize, "Expected array of type [" + getClass.getSimpleName + "] to have [" + expectedSize + "] elements.")

  def this(expectedSize: Int, value: T*)(using Quotes)  = this(expectedSize, List(value*))

end StaticArray