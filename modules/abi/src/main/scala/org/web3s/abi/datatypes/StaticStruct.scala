
package org.web3s.abi.datatypes

import java.util
import scala.quoted.{Quotes, Type}

class StaticStruct[T <: SolidityType[_] : Type](val values: List[T]
                                               )(using Quotes) extends StaticArray[T](values.size,values) with StructType {



  def this(values: T*)(using Quotes) = this(List(values*))

  override def getTypeAsString: String = values.map(_.getTypeAsString).mkString("(", ",", ")")
}