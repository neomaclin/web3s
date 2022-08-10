package org.web3s.abi.datatypes

import org.web3s.abi.datatypes.EthType.MAX_BYTE_LENGTH

abstract class BytesType(val value: Array[Byte], val `type`: String) extends EthType[Array[Byte]] :
  override def bytes32PaddedLength: Int =
    if (value.length <= 32) MAX_BYTE_LENGTH
    else (value.length / MAX_BYTE_LENGTH + 1) * MAX_BYTE_LENGTH
  
  override def equals(o: Any): Boolean = o match
    case other: BytesType => (value sameElements other.value) && `type` == other.`type`
    case _ => false
  
  override def hashCode: Int = 31 * value.hashCode + `type`.hashCode
