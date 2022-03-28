package org.web3s.crypto

import org.web3s.crypto.Keys.ADDRESS_SIZE
import org.web3s.rlp.{Encoder, RlpList, RlpString, RlpType}
import org.web3s.utils.Numeric

object ContractUtils:

  val SALT_SIZE: Int = 32
  val ADDRESS_BYTE_SIZE: Int = ADDRESS_SIZE / 8

  def generateContractAddress(address: Array[Byte], nonce: BigInt): Array[Byte] =
    var values = List.empty[RlpType]
    values = RlpString(address) :: values
    values = RlpString.from(nonce) :: values

    val rlpList = RlpList(values.reverse.toVector)
    val encoded = Encoder.encode(rlpList)
    val hashed = Hash.sha3(encoded)
    hashed.drop(12)
  end generateContractAddress

  def generateContractAddress(address: String, nonce: BigInt): String =
    Numeric.toHexString(generateContractAddress(Numeric.hexStringToByteArray(address), nonce))
  
  @throws[RuntimeException]
  def generateCreate2ContractAddress(address: Array[Byte], salt: Array[Byte], initCode: Array[Byte]): Array[Byte] =
    if (address.length != ADDRESS_BYTE_SIZE) throw new RuntimeException("Invalid address size")
    if (salt.length != SALT_SIZE) throw new RuntimeException("Invalid salt size")
    val hashedInitCode = Hash.sha3(initCode)
    val buffer = new Array[Byte](1 + address.length + salt.length + hashedInitCode.length)
    buffer(0) = 0xff.toByte
    var offset = 1
    Array.copy(address, 0, buffer, offset, address.length)
    offset += address.length
    Array.copy(salt, 0, buffer, offset, salt.length)
    offset += salt.length
    Array.copy(hashedInitCode, 0, buffer, offset, hashedInitCode.length)
    val hashed = Hash.sha3(buffer)
    hashed.drop(12)
  end generateCreate2ContractAddress

  def generateCreate2ContractAddress(address: String, salt: Array[Byte], initCode: Array[Byte]): String =
    Numeric.toHexString(generateCreate2ContractAddress(Numeric.hexStringToByteArray(address), salt, initCode))
  
end ContractUtils
