package org.web3s.abi.datatypes

import scala.quoted.{Quotes, Type}


class DynamicStruct[T <: SolidityType[_] : Type](override val value: List[T])(using Quotes) extends DynamicArray[T](value) with StructType {

  def this(value: T*)(using Quotes) = this(List(value*))

  override def bytes32PaddedLength: Int = super.bytes32PaddedLength + 32

  override def getTypeAsString: String = value.map(_.getTypeAsString).mkString("(", ",", ")")
}