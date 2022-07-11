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
package org.web3s.crypto

import org.scalatest.funsuite.AnyFunSuite

class CredentialsTest extends AnyFunSuite:
  
  test("CredentialsFromString")  {
    val credentials = Credentials.create(SampleKeys.KEY_PAIR)
    verify(credentials)
  }
  
  test("CredentialsFromECKeyPair")  {
    val credentials = Credentials.create(SampleKeys.PRIVATE_KEY_STRING, SampleKeys.PUBLIC_KEY_STRING)
    verify(credentials)
  }

  private def verify(credentials: Credentials) = {
    assert(credentials.address == SampleKeys.ADDRESS)
    assert(credentials.ecKeyPair == SampleKeys.KEY_PAIR)
  }
  
end CredentialsTest
