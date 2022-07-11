package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.{StaticArray, SolidityType}
import izumi.reflect.Tag

final class StaticArray29[T <: SolidityType[_] : Tag](values: List[T]) extends StaticArray(29, values) :
  def this(values: T*) = this(List(values*))
end StaticArray29

