
package org.web3s.abi.datatypes

import scala.quoted._

object SolidityArray:
  final val MAX_SIZE_OF_STATIC_ARRAY = 32
end SolidityArray

abstract class SolidityArray[T <: SolidityType[_] : Type](val value: List[T])(using Quotes) extends SolidityType[List[T]] :
  
  def this(expectedSize: Int, values: T*)(using Quotes) = this(List(values *))

  override def bytes32PaddedLength: Int = value.foldLeft(0)(_ + _.bytes32PaddedLength)

  def getComponentType: Type[T] = Type.of[T]

  override def getTypeAsString: String = Type.show[T]

end SolidityArray
