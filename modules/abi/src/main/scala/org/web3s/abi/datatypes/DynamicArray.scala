package org.web3s.abi.datatypes

import izumi.reflect.Tag

class DynamicArray[T <: SolidityType[_] : Tag](val values: List[T]) extends SolidityArray[T](values) :

  override def bytes32PaddedLength: Int = super.bytes32PaddedLength + SolidityType.MAX_BYTE_LENGTH

  override def getTypeAsString: String = values.map(_.getTypeAsString).mkString("[", ",", "]")

end DynamicArray
