package org.web3s.abi.datatypes

import izumi.reflect.Tag

abstract class StaticArray[T <: SolidityType[_] : Tag](expectedSize: Int,
                                                        override val value: List[T]
                                                       )extends SolidityArray[T](value):

  require(value.size <= SolidityArray.MAX_SIZE_OF_STATIC_ARRAY, "Static arrays with a length greater than " + SolidityArray.MAX_SIZE_OF_STATIC_ARRAY + " are not supported.")

  require(value.size == expectedSize, "Expected array of type [" + getClass.getSimpleName + "] to have [" + expectedSize + "] elements.")

  def this(expectedSize: Int, value: T*) = this(expectedSize, List(value*))

end StaticArray