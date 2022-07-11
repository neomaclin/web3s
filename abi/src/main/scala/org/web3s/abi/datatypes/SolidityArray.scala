
package org.web3s.abi.datatypes

import izumi.reflect.Tag
import org.web3s.abi.{SolidityTypes, TypeEncoder}

object SolidityArray:
  final val MAX_SIZE_OF_STATIC_ARRAY = 32

  // def encode[T <: SolidityType[_] : Tag](value: SolidityArray[T]): String =
  //   value.value.map(TypeEncoder.encode[T](_)).mkString
  // end encode

  //  def decode[T <: SolidityArray[_] : Tag](encoded: String): SolidityArray[T] =
  //
  //  end decode

end SolidityArray

abstract class SolidityArray[+T <: SolidityType[_] : Tag](override val values: List[T]) extends SoliditySeqType[T] :

  def this(expectedSize: Int, values: T*) = this(List(values *))

  override def bytes32PaddedLength: Int = values.foldLeft(0)(_ + _.bytes32PaddedLength)

  override def getTypeAsString: String = SolidityTypes.getTypeAString[T] + "[" + values.length + "]"

end SolidityArray
