
package org.web3s.abi.datatypes

import izumi.reflect.Tag
import org.web3s.abi.{Encodable, TypeEncoder}

object StaticStruct:
  
  def encode[T <: SolidityType[_] : Tag : Encodable, S <: StaticStruct[T] ](value: S): String =
    value.value.map(TypeEncoder.encode[T](_)).mkString
  
end StaticStruct


class StaticStruct[+T <: SolidityType[_] : Tag](val values: List[T]
                                              ) extends StaticArray[T](values.size, values) with StructType {

  def this(values: T*) = this(List(values *))

  override def getTypeAsString: String = values.map(_.getTypeAsString).mkString("(", ",", ")")
}