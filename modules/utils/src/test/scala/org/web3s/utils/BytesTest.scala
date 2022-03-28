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
package org.web3s.utils

//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.Assertions.assert

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.utils.Bytes.trimLeadingZeroes

class BytesTest extends AnyFunSuite :

  test("TrimLeadingZeroes") {
    assert(trimLeadingZeroes(Array.emptyByteArray) sameElements Array.emptyByteArray)
    assert(trimLeadingZeroes(Array.apply[Byte](0)) sameElements Array.apply[Byte](0))
    assert(trimLeadingZeroes(Array.apply[Byte](1)) sameElements Array.apply[Byte](1))
    assert(trimLeadingZeroes(Array.apply[Byte](0, 1)) sameElements Array.apply[Byte](1))
    assert(trimLeadingZeroes(Array.apply[Byte](0, 0, 1)) sameElements Array.apply[Byte](1))
    assert(trimLeadingZeroes(Array.apply[Byte](0, 0, 1, 0)) sameElements Array.apply[Byte](1, 0))
  }

end BytesTest
