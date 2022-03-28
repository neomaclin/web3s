/*
 * Copyright 2019 Web3 Labs Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *//*
 * Copyright 2019 Web3 Labs Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.web3s.abi.datatypes

import org.web3s.abi.datatypes.AbiType.MAX_BYTE_LENGTH

/** UTF-8 encoded string type. */
object Utf8String:
  val TYPE_NAME = "string"
  val DEFAULT = new Utf8String("")
end Utf8String


class Utf8String(override val value: String) extends AbiType[String] :
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
