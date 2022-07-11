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
import org.web3s.crypto.transaction.{RawTransaction, Transaction}
import org.web3s.rlp.RlpString
import org.web3s.utils.Numeric

class TransactionEncoderTest extends AnyFunSuite :
    private def createEtherTransaction = RawTransaction.createEtherTransaction(BigInt(0), BigInt(1), BigInt(10), "0xadd5355", BigInt(Long.MaxValue))

    private def createContractTransaction = RawTransaction.createContractTransaction(BigInt(0), BigInt(1), BigInt(10), BigInt(Long.MaxValue), "01234566789")

    private def createEip155RawTransaction = RawTransaction.createEtherTransaction(BigInt(9), BigInt(20000000000L), BigInt(21000), "0x3535353535353535353535353535353535353535", BigInt(1000000000000000000L))

    private def createEip1559RawTransaction = RawTransaction.createEtherTransaction(1559L, BigInt(0), BigInt(30000), "0x627306090abaB3A6e1400e9345bC60c78a8BEf57", BigInt(123), BigInt(5678), BigInt(1100000))

    test("SignMessage"){
      val signedMessage = Transaction.Encoder.signMessage(createEtherTransaction, SampleKeys.CREDENTIALS)
      val hexMessage = Numeric.toHexString(signedMessage)
      assert(hexMessage ==  "0xf85580010a840add5355887fffffffffffffff80" + "1c" + "a046360b50498ddf5566551ce1ce69c46c565f1f478bb0ee680caf31fbc08ab727" + "a01b2f1432de16d110407d544f519fc91b84c8e16d3b6ec899592d486a94974cd0")
    }

    test("EtherTransactionAsRlpValues"){
      val rlpStrings = Transaction.Encoder.asRlpValues(createEtherTransaction, Sign.SignatureData(Array[Byte](0.toByte), new Array[Byte](32), new Array[Byte](32)))
      assert(rlpStrings.size == 9)
      assert(rlpStrings(3) == RlpString.from(BigInt("add5355", 16)))
    }

    test("ContractAsRlpValues") {
      val rlpStrings = Transaction.Encoder.asRlpValues(createContractTransaction, Sign.SignatureData(Array.emptyByteArray, Array.emptyByteArray,Array.emptyByteArray))
      assert(rlpStrings.size == 6)
      assert(rlpStrings(3) == RlpString.from(""))
    }

    test("Eip155Encode"){
      assert(Transaction.Encoder.encode(createEip155RawTransaction, 1.toByte) sameElements Numeric.hexStringToByteArray("0xec098504a817c800825208943535353535353535353535353535353535353535880de0" + "b6b3a764000080018080"))
    }

    test("Eip155Transaction") { // https://github.com/ethereum/EIPs/issues/155
      val credentials = Credentials.create("0x4646464646464646464646464646464646464646464646464646464646464646")
      assert(Transaction.Encoder.signMessage(createEip155RawTransaction, 1.toByte, credentials)  sameElements Numeric.hexStringToByteArray("0xf86c098504a817c800825208943535353535353535353535353535353535353535880" + "de0b6b3a76400008025a028ef61340bd939bc2195fe537567866003e1a15d" + "3c71ff63e1590620aa636276a067cbe9d8997f761aecb703304b3800ccf55" + "5c9f3dc64214b297fb1966a3b6d83"))
    }

    test("Eip155TransactionWithLargeChainId")  {
      val credentials = Credentials.create("0x4646464646464646464646464646464646464646464646464646464646464646")
      assert(Transaction.Encoder.signMessage(createEip155RawTransaction, Long.MaxValue, credentials) sameElements  Numeric.hexStringToByteArray("0xf875098504a817c800825208943535353535353535353535353535353535353535880de0b6b3a76400008089010000000000000021a0ed14bd16ddd7788623f4439db3ddbc8bf548c241c3af87819c187a638ef40e17a03b4972ee3adb77b6b06784d12fe098c2cb84c03afd79d17b1caf8f63483101f0"))
    }

    test("Eip1559Transaction") { // https://github.com/ethereum/EIPs/blob/master/EIPS/eip-1559.md
      val credentials = Credentials.create("0x4646464646464646464646464646464646464646464646464646464646464646")
      assert(Transaction.Encoder.signMessage(createEip1559RawTransaction, credentials) sameElements Numeric.hexStringToByteArray("02f8698206178082162e8310c8e082753094627306090abab3a6e1400e9345bc60c78a8bef577b80c001a0d1f9ee3bdde4d4e0792c7089b84059fb28e17f494556d8a775450b1dd6c318a1a038bd3e2fb9e018528e0a41f57c7a32a8d23b2693e0451aa6ef4519b234466e7f"))
      assert(Transaction.Encoder.signMessage(createEip1559RawTransaction, 1559L, credentials) sameElements Numeric.hexStringToByteArray("02f8698206178082162e8310c8e082753094627306090abab3a6e1400e9345bc60c78a8bef577b80c001a0d1f9ee3bdde4d4e0792c7089b84059fb28e17f494556d8a775450b1dd6c318a1a038bd3e2fb9e018528e0a41f57c7a32a8d23b2693e0451aa6ef4519b234466e7f"))
    }
end TransactionEncoderTest
