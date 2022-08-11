package org.web3s.abi.datatypes

import izumi.reflect.Tag

final class DynamicStruct[E <: EthType[_] : Tag, T <: Tuple](override val value: E *: T) extends StructType[E *: T] {

 // def this() = this(EmptyTuple)

 // override def bytes32PaddedLength: Int = super.bytes32PaddedLength + 32

  //override def getTypeAsString: String = values.map(_.getTypeAsString).mkString("(", ",", ")")
}
