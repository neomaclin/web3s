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

import org.web3s.utils.Numeric

/**
 * Address type, which by default is equivalent to uint160 which follows the Ethereum specification.
 */
object Address {
  val TYPE_NAME = "address"
  val DEFAULT_LENGTH = 160
  val DEFAULT = new Address(BigInt(0))
}

class Address(_value: SolidityUInt) extends SolidityType[String] :

  def this(bitSize: Int, value: BigInt) = this(new SolidityUInt(bitSize, value))

  def this(value: BigInt) = this(Address.DEFAULT_LENGTH, value)

  def this(bitSize: Int, hexValue: String) = this(bitSize, Numeric.toBigInt(hexValue))

  def this(hexValue: String) = this(Address.DEFAULT_LENGTH, hexValue)

  def getTypeAsString: String = Address.TYPE_NAME

  override def value: String = toString
  
  override def toString: String = Numeric.toHexStringWithPrefixZeroPadded(_value.value, _value.bitSize >> 2)

  override def equals(o: Any): Boolean = o match
    case other: Address => other.value == value
    case _ => false

  override def hashCode: Int = if (value != null) value.hashCode else 0
  
end Address
