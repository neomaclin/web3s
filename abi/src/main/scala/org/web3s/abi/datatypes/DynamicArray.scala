package org.web3s.abi.datatypes

import izumi.reflect.Tag
import izumi.reflect.macrortti.LightTypeTagUnpacker
import org.web3s.abi.codec.{Encodable, TypeEncoder}
import org.web3s.abi.EthTypes
import org.web3s.abi.datatypes.EthType.MAX_BYTE_LENGTH
import org.web3s.utils.Numeric

class DynamicArray[T <: EthType[_] : Tag](override val value: T*) extends EthArray[T](value) :
  override def bytes32PaddedLength: Int = super.bytes32PaddedLength + EthType.MAX_BYTE_LENGTH

  // override def getTypeAsString: String = EthTypes.getTypeAString[T] + "[]"

