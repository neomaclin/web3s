package org.web3s.abi.datatypes

import izumi.reflect.Tag

class DynamicStruct[T <: SolidityType[_] : Tag](override val value: List[T]) extends DynamicArray[T](value) with StructType {

  def this(value: T*) = this(List(value*))

  override def bytes32PaddedLength: Int = super.bytes32PaddedLength + 32

  override def getTypeAsString: String = value.map(_.getTypeAsString).mkString("(", ",", ")")
}