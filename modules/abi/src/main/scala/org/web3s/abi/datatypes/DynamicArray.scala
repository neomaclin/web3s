package org.web3s.abi.datatypes

import scala.quoted.{Quotes, Type}


class DynamicArray[T <: SolidityType[_] : Type](val values: List[T])(using Quotes) extends SolidityArray[T](values) :

  override def bytes32PaddedLength: Int = super.bytes32PaddedLength + SolidityType.MAX_BYTE_LENGTH

  override def getTypeAsString: String = values.map(_.getTypeAsString).mkString("[", ",", "]")

end DynamicArray
