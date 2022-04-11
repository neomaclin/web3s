package org.web3s.abi.datatypes.generated

import org.web3s.abi.datatypes.{StaticArray, SolidityType}
import scala.quoted.{Quotes, Type}

final class StaticArray7[T <: SolidityType[_] : Type](values: List[T])(using Quotes) extends StaticArray(7, values) :
  def this(values: T*)(using Quotes) = this(List(values*))
end StaticArray7

