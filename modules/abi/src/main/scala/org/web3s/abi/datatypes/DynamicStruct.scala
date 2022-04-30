package org.web3s.abi.datatypes

import izumi.reflect.Tag


class DynamicStruct[T <: SolidityType[_] : Tag](override val values: List[T]) extends DynamicArray[T](values) with StructType {

  def this() = this(Nil)
  def this(values: T*) = this(List(values*))

  override def bytes32PaddedLength: Int = super.bytes32PaddedLength + 32

  override def getTypeAsString: String = values.map(_.getTypeAsString).mkString("(", ",", ")")
}