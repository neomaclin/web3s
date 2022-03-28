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

/** Statically allocated sequence of bytes. */
object Bytes:
  val TYPE_NAME = "bytes"
end Bytes

final class Bytes(val byteSize: Int,
                  override val value: Array[Byte]) extends BytesType(value, Bytes.TYPE_NAME + value.length):
  require(isValid(byteSize), "Input byte array must be in range 0 < M <= 32 and length must match type")

  private def isValid(byteSize: Int) =
    val length = value.length
    length > 0 && length <= 32 && length == byteSize

end Bytes
