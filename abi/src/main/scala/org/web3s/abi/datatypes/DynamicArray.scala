package org.web3s.abi.datatypes

import org.web3s.abi.codec.{Encodable, TypeEncoder}
import org.web3s.abi.EthTypes
import org.web3s.abi.datatypes.EthType.MAX_BYTE_LENGTH
import org.web3s.utils.Numeric
import izumi.reflect.Tag
import scala.reflect.ClassTag
final case class DynamicArray[T <: EthType[_] : Tag: ClassTag](values: T*) extends EthArray[T] :
  override def bytes32PaddedLength: Int = super.bytes32PaddedLength + EthType.MAX_BYTE_LENGTH
  override def value: Array[T] = values.toArray
  //override def getTypeAsString: String = EthTypes.getTypeAString[T] + "[]"

