package org.web3s.ens

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.ens.exceptions.EnsResolutionException

class NameHashTest extends AnyFunSuite :

  import NameHash._

  test("NameHash") {
    assert(nameHash("") == "0x0000000000000000000000000000000000000000000000000000000000000000")
    assert(nameHash("eth") == "0x93cdeb708b7545dc668eb9280176169d1c33cfd8ed6f04690a0bcc88a93fc4ae")
    assert(nameHash("foo.eth") == "0xde9b09fd7c5f901e23a3f19fecc54828e9c848539801e86591bd9801b019f84f")
  }

  test("Normalise") {
    assert(normalise("foo") == "foo")
    assert(normalise("foo.bar.baz.eth") == "foo.bar.baz.eth")
    assert(normalise("fOo.eth") == "foo.eth")
    assert(normalise("foo-bar.eth") == "foo-bar.eth")

    assert(normalise("Obb.at") == "obb.at")
    assert(normalise("TESTER.eth") == "tester.eth")
    assert(normalise("test\u200btest.com") == "testtest.com")
  }

  test("NormaliseInvalid") {
    def testInvalidName(ensName: String) = {
      assertThrows[EnsResolutionException] {
        normalise(ensName)
      }
    }

    testInvalidName("foo..bar")
    testInvalidName("ba\\u007Fr.eth")
    testInvalidName("-baz.eth-")
    testInvalidName("foo_bar.eth")
  }
  
//  test("DnsEncode") {
//    val dnsEncoded = dnsEncode("1.offchainexample.eth")
//
//    assert("0x01310f6f6666636861696e6578616d706c650365746800" == dnsEncoded)
//  }