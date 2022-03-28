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

import org.bouncycastle.crypto.digests.{RIPEMD160Digest, SHA512Digest}
import org.bouncycastle.crypto.macs.HMac
import org.bouncycastle.crypto.params.KeyParameter
import org.bouncycastle.jcajce.provider.digest.{Blake2b, Keccak}
import org.web3s.utils.Numeric

import java.nio.charset.StandardCharsets
import java.security.{MessageDigest, NoSuchAlgorithmException}

object Hash:

  @throws[RuntimeException]
  def hash(input: Array[Byte], algorithm: String): Array[Byte] = try
    val digest = MessageDigest.getInstance(algorithm.toUpperCase)
    digest.digest(input)
  catch
    case e: NoSuchAlgorithmException =>
      throw new RuntimeException("Couldn't find a " + algorithm + " provider", e)
  end hash


  def sha3(hexInput: String): String =
    val bytes = Numeric.hexStringToByteArray(hexInput)
    val result = sha3(bytes)
    Numeric.toHexString(result)
  end sha3


  def sha3(input: Array[Byte], offset: Int, length: Int): Array[Byte] =
    val kecc = new Keccak.Digest256
    kecc.update(input, offset, length)
    kecc.digest
  end sha3

  def sha3(input: Array[Byte]): Array[Byte] = sha3(input, 0, input.length)

  def sha3String(utf8String: String): String = Numeric.toHexString(sha3(utf8String.getBytes(StandardCharsets.UTF_8)))

  def sha256(input: Array[Byte]): Array[Byte] = try
    val digest = MessageDigest.getInstance("SHA-256")
    digest.digest(input)
  catch
    case e: NoSuchAlgorithmException =>
      throw new RuntimeException("Couldn't find a SHA-256 provider", e)
  end sha256

  def hmacSha512(key: Array[Byte], input: Array[Byte]): Array[Byte] =
    val hMac = new HMac(new SHA512Digest)
    hMac.init(new KeyParameter(key))
    hMac.update(input, 0, input.length)
    val out = new Array[Byte](64)
    hMac.doFinal(out, 0)
    out
  end hmacSha512

  def sha256hash160(input: Array[Byte]): Array[Byte] =
    val sha256v = sha256(input)
    val digest = new RIPEMD160Digest
    digest.update(sha256v, 0, sha256v.length)
    val out = new Array[Byte](20)
    digest.doFinal(out, 0)
    out
  end sha256hash160

  def blake2b256(input: Array[Byte]): Array[Byte] = new Blake2b.Blake2b256().digest(input)

end Hash

