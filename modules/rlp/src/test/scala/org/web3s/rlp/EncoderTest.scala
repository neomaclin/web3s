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
package org.web3s.rlp

import org.scalatest.funsuite.AnyFunSuite

class EncoderTest extends AnyFunSuite :

  test("Encode") {
      assert(Encoder.encode(RlpString.from("dog")) sameElements  Array[Byte](0x83.toByte, 'd', 'o', 'g'))
      assert(Encoder.encode(new RlpList(RlpString.from("cat"), RlpString.from("dog"))) sameElements Array[Byte](0xc8.toByte, 0x83.toByte, 'c', 'a', 't', 0x83.toByte, 'd', 'o', 'g'))
      assert(Encoder.encode(RlpString.from("")) sameElements Array[Byte](0x80.toByte))
      assert(Encoder.encode(RlpString(Array.emptyByteArray)) sameElements Array[Byte](0x80.toByte))
      assert(Encoder.encode(RlpList()) sameElements Array[Byte](0xc0.toByte))
      assert(Encoder.encode(RlpString.from(BigInt(0x0f))) sameElements Array[Byte](0x0f.toByte))
      assert(Encoder.encode(RlpString.from(BigInt(0x0400))) sameElements Array[Byte](0x82.toByte, 0x04.toByte, 0x00.toByte))

      assert(
          Encoder.encode(
              new RlpList(
                  new RlpList(),
                  new RlpList(new RlpList()),
                  new RlpList(new RlpList(), new RlpList(new RlpList())))) sameElements Array[Byte](
               0xc7.toByte,
               0xc0.toByte,
               0xc1.toByte,
               0xc0.toByte,
               0xc3.toByte,
               0xc0.toByte,
               0xc1.toByte,
               0xc0.toByte
          )
      )

      assert(Encoder.encode(RlpString.from("Lorem ipsum dolor sit amet, consectetur adipisicing elit")) sameElements Array[Byte](0xb8.toByte, 0x38.toByte, 'L', 'o', 'r', 'e', 'm', ' ', 'i', 'p', 's', 'u', 'm', ' ', 'd', 'o', 'l', 'o', 'r', ' ', 's', 'i', 't', ' ', 'a', 'm', 'e', 't', ',', ' ', 'c', 'o', 'n', 's', 'e', 'c', 't', 'e', 't', 'u', 'r', ' ', 'a', 'd', 'i', 'p', 'i', 's', 'i', 'c', 'i', 'n', 'g', ' ', 'e', 'l', 'i', 't'))
      assert(Encoder.encode(RlpString.from(BigInt(0))) sameElements Array[Byte](0x80.toByte))
      // https://github.com/paritytech/parity-common/blob/master/rlp/tests/tests.rs#L237
      assert(Encoder.encode(RlpString(Array[Byte](0))) sameElements Array[Byte](0x00.toByte))
      assert(Encoder.encode(new RlpList(RlpString.from("zw"), new RlpList(RlpString.from(4)), RlpString.from(1))) sameElements Array[Byte](0xc6.toByte, 0x82.toByte, 0x7a.toByte, 0x77.toByte, 0xc1.toByte, 0x04.toByte, 0x01.toByte))
      // 55 bytes. See https://github.com/web3j/web3j/issues/519
      val encodeMe = Array.fill[Byte](55)(0.toByte)
      val expectedEncoding = new Array[Byte](56)
      expectedEncoding(0) = 0xb7.toByte
      Array.copy(encodeMe, 0, expectedEncoding, 1, encodeMe.length)
      assert(Encoder.encode(RlpString(encodeMe)) sameElements expectedEncoding)
    }
end EncoderTest
