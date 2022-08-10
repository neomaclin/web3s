
package org.web3s.abi.datatypes

import org.web3s.abi.codec.Encodable
import org.web3s.abi.datatypes
import org.web3s.abi.datatypes.EthType.MAX_BYTE_LENGTH

/** UTF-8 encoded string type. */
object EthUtf8String:
  val DEFAULT = EthUtf8String("")

final class EthUtf8String(override val value: String) extends EthType[String] :
  /**
   * Returns the Bytes32 Padded length. If the string is empty, we only encode its length. Else,
   * we concatenate its length along of its value
   */
  override def bytes32PaddedLength: Int = if (value.isEmpty) MAX_BYTE_LENGTH else 2 * MAX_BYTE_LENGTH
  
 // override def getTypeAsString: String = EthUtf8String.TYPE_NAME

  override def equals(o: Any): Boolean = o match
    case other: EthUtf8String => other.value == value
    case _ => false

  override def hashCode: Int = if (value != null) value.hashCode else 0

  override def toString: String = value

