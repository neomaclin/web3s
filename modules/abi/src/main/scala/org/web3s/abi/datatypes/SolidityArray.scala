
package org.web3s.abi.datatypes

import izumi.reflect.Tag

object SolidityArray:
  final val MAX_SIZE_OF_STATIC_ARRAY = 32
end SolidityArray

abstract class SolidityArray[T <: SolidityType[_] : Tag](val value: List[T]) extends SolidityType[List[T]] :
  
  def this(expectedSize: Int, values: T*) = this(List(values *))

  override def bytes32PaddedLength: Int = value.foldLeft(0)(_ + _.bytes32PaddedLength)

  def getComponentType: Tag[T] = Tag[T]

  override def getTypeAsString: String = Tag[T].toString

end SolidityArray
