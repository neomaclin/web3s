
package org.web3s.crypto

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.crypto.ContractUtils.*
import org.web3s.utils.Numeric.hexStringToByteArray

class ContractUtilsTest extends AnyFunSuite :

  test("CreateContractAddress") {
    val address = "0x19e03255f667bdfd50a32722df860b1eeaf4d635"
    assert(generateContractAddress(address, BigInt(209)) == "0xe41e694d8fa4337b7bffc7483d3609ae1ea068d5")
    assert(generateContractAddress(address, BigInt(257)) == "0x59c21d36fbe415218e834683cb6616f2bc971ca9")
  }

  test("EIP1014Create2ContractAddress") { // https://eips.ethereum.org/EIPS/eip-1014
    // example 0
    assert(Keys.toChecksumAddress(generateCreate2ContractAddress("0x0000000000000000000000000000000000000000", hexStringToByteArray("0x0000000000000000000000000000000000000000000000000000000000000000"), hexStringToByteArray("0x00"))) == "0x4D1A2e2bB4F88F0250f26Ffff098B0b30B26BF38")
    // example 1
    assert(Keys.toChecksumAddress(generateCreate2ContractAddress("0xdeadbeef00000000000000000000000000000000", hexStringToByteArray("0x0000000000000000000000000000000000000000000000000000000000000000"), hexStringToByteArray("0x00"))) == "0xB928f69Bb1D91Cd65274e3c79d8986362984fDA3")
    // example 2
    assert(Keys.toChecksumAddress(generateCreate2ContractAddress("0xdeadbeef00000000000000000000000000000000", hexStringToByteArray("0x000000000000000000000000feed000000000000000000000000000000000000"), hexStringToByteArray("0x00"))) == "0xD04116cDd17beBE565EB2422F2497E06cC1C9833")
    // example 3
    assert(Keys.toChecksumAddress(generateCreate2ContractAddress("0x0000000000000000000000000000000000000000", hexStringToByteArray("0x0000000000000000000000000000000000000000000000000000000000000000"), hexStringToByteArray("0xdeadbeef"))) == "0x70f2b2914A2a4b783FaEFb75f459A580616Fcb5e")
    // example 4
    assert(Keys.toChecksumAddress(generateCreate2ContractAddress("0x00000000000000000000000000000000deadbeef", hexStringToByteArray("0x00000000000000000000000000000000000000000000000000000000cafebabe"), hexStringToByteArray("0xdeadbeef"))) == "0x60f3f640a8508fC6a86d45DF051962668E1e8AC7")
    // example 5
    assert(Keys.toChecksumAddress(generateCreate2ContractAddress("0x00000000000000000000000000000000deadbeef", hexStringToByteArray("0x00000000000000000000000000000000000000000000000000000000cafebabe"), hexStringToByteArray("0xdeadbeefdeadbeefdeadbeefdeadbeefdeadbeefdeadbeefdeadbeefdeadbeefdeadbeefdeadbeefdeadbeef"))) == "0x1d8bfDC5D46DC4f61D6b6115972536eBE6A8854C")
    // example 6
    assert(Keys.toChecksumAddress(generateCreate2ContractAddress("0x0000000000000000000000000000000000000000", hexStringToByteArray("0x0000000000000000000000000000000000000000000000000000000000000000"), hexStringToByteArray("0x"))) == "0xE33C0C7F7df4809055C3ebA6c09CFe4BaF1BD9e0")
  }

end ContractUtilsTest
