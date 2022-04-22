
package org.web3s.abi.datatypes

import izumi.reflect.Tag
import org.web3s.abi.TypeEncoder

object SolidityArray:
  final val MAX_SIZE_OF_STATIC_ARRAY = 32

  // def encode[T <: SolidityType[_] : Tag](value: SolidityArray[T]): String =
  //   value.value.map(TypeEncoder.encode[T](_)).mkString
  // end encode

//  def decode[T <: SolidityType[_] : Tag](encoded: String): SolidityArray[T] =
//
//  end decode

end SolidityArray

abstract class SolidityArray[T <: SolidityType[_] : Tag](val value: List[T]) extends SolidityType[List[T]] :
  
  def this(expectedSize: Int, values: T*) = this(List(values *))

  override def bytes32PaddedLength: Int = value.foldLeft(0)(_ + _.bytes32PaddedLength)

  def getComponentType: Tag[T] = Tag[T]

  override def getTypeAsString: String = Tag[T].toString

end SolidityArray
