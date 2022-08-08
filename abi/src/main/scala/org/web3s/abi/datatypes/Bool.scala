package org.web3s.abi.datatypes

import izumi.reflect.Tag
import org.web3s.abi.codec.{Decodable, Encodable}
import org.web3s.abi.datatypes
import org.web3s.abi.datatypes.EthType.MAX_BYTE_LENGTH
import org.web3s.utils.Numeric



object Bool:
  val DEFAULT = new Bool(false)
  val MAX_BYTE_LENGTH_FOR_HEX_STRING: Int = MAX_BYTE_LENGTH << 1
  given (Boolean => Bool) = new Bool(_)

final class Bool(override val value: Boolean) extends EthType[Boolean]:
  override def equals(o: Any): Boolean = o match 
    case other: Bool =>  value == other.value
    case _ => false
  override def hashCode: Int = if (value) 1 else 0
  