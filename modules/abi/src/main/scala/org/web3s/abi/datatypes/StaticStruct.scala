
package org.web3s.abi.datatypes

import izumi.reflect.Tag


class StaticStruct[T <: SolidityType[_] : Tag](val values: List[T]
                                              ) extends StaticArray[T](values.size, values) with StructType {

  def this(values: T*) = this(List(values *))

  override def getTypeAsString: String = values.map(_.getTypeAsString).mkString("(", ",", ")")
}