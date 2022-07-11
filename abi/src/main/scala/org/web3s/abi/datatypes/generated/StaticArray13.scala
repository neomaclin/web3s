package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.{StaticArray, SolidityType}
import izumi.reflect.Tag

final class StaticArray13[T <: SolidityType[_] : Tag](values: List[T]) extends StaticArray(13, values) :
  def this(values: T*) = this(List(values*))
end StaticArray13

