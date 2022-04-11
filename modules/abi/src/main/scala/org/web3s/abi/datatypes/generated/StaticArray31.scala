package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.{StaticArray, SolidityType}
import scala.quoted.{Quotes, Type}

final class StaticArray31[T <: SolidityType[_] : Type](values: List[T])(using Quotes) extends StaticArray(31, values) :
  def this(values: T*)(using Quotes) = this(List(values*))
end StaticArray31

