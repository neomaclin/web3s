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

import org.web3s.abi.datatypes.SolidityType.MAX_BIT_LENGTH

object SolidityUInt:
  val TYPE_NAME = "uint"
  val DEFAULT = new SolidityUInt(BigInt(0))
end SolidityUInt


class SolidityUInt(override val bitSize: Int,
                   override val value: BigInt) extends IntType(SolidityUInt.TYPE_NAME, bitSize, value):
  
  def this(value: BigInt) = this(MAX_BIT_LENGTH, value)// "int" values should be declared as int256 in computing function selectors
  
  override def valid: Boolean = super.valid && 0 <= value.signum

end SolidityUInt
