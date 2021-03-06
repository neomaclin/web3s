/*
 * Copyright 2022 Web3 Labs Ltd.
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
 * Copyright 2022 Web3 Labs Ltd.
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
package org.web3s.crypto

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.utils.Numeric

class CryptoUtilsTest extends AnyFunSuite :

  private val TX_SIGN_FORMAT_DER_HEX = "3044022046360b50498ddf5566551ce1ce69c46c565f1f478bb0ee680caf31fbc08ab72702201b2f1432de16d110407d544f519fc91b84c8e16d3b6ec899592d486a94974cd0"

  private val ecdsaSignatureExample = ECDSASignature(BigInt("31757387226078388218879983949976195107190435398991401751066049942728919922471"), BigInt("12295628130105760695929025310777619861262324839583119058006543455570535861456"))
  test("toDerFormat") {
    val signDER = CryptoUtils.toDerFormat(ecdsaSignatureExample)
    assert(Numeric.hexStringToByteArray(TX_SIGN_FORMAT_DER_HEX) sameElements signDER)
  }

  test("fromDerFormat") {
    val ecdsaSignature = CryptoUtils.fromDerFormat(Numeric.hexStringToByteArray(TX_SIGN_FORMAT_DER_HEX))
    assert(ecdsaSignatureExample.r == ecdsaSignature.r)
    assert(ecdsaSignatureExample.s == ecdsaSignature.s)
  }

end CryptoUtilsTest