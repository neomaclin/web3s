
package org.web3s.abi.datatypes

import org.web3s.abi.{Encodable, datatypes}
import org.web3s.abi.datatypes.SolidityType.MAX_BYTE_LENGTH

/** UTF-8 encoded string type. */
object Utf8String:

  given Encodable[Utf8String] = Utf8String.encode(_)

  val TYPE_NAME = "string"
  val DEFAULT = new Utf8String("")
  
  def encode(value: Utf8String):String = DynamicBytes.encode(new datatypes.DynamicBytes(value.value.getBytes))
  
 // def decode(input: String, offset: Int): Utf8String = new Utf8String(new String(DynamicBytes.decode(input, offset).value))
  
end Utf8String


class Utf8String(override val value: String) extends SolidityType[String] :
  /**
   * Returns the Bytes32 Padded length. If the string is empty, we only encode its length. Else,
   * we concatenate its length along of its value
   */
  override def bytes32PaddedLength: Int = if (value.isEmpty) MAX_BYTE_LENGTH else 2 * MAX_BYTE_LENGTH
  
  override def getTypeAsString: String = Utf8String.TYPE_NAME

  override def equals(o: Any): Boolean = o match
    case other: Utf8String => other.value == value
    case _ => false

  override def hashCode: Int = if (value != null) value.hashCode else 0

  override def toString: String = value

end Utf8String
