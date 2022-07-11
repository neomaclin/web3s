package org.web3s.crypto

import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.web3s.crypto.SecureRandomUtils
import org.web3s.utils.Numeric

import java.security.*
import java.security.spec.ECGenParameterSpec


object Keys:
  val PRIVATE_KEY_SIZE: Int = 32
  val PUBLIC_KEY_SIZE: Int = 64
  val ADDRESS_SIZE: Int = 160
  val ADDRESS_LENGTH_IN_HEX: Int = ADDRESS_SIZE >> 2
  val PUBLIC_KEY_LENGTH_IN_HEX: Int = PUBLIC_KEY_SIZE << 1
  val PRIVATE_KEY_LENGTH_IN_HEX: Int = PRIVATE_KEY_SIZE << 1

  Security.addProvider(new BouncyCastleProvider)

  @throws[NoSuchProviderException]
  @throws[NoSuchAlgorithmException]
  @throws[InvalidAlgorithmParameterException]
  def createSecp256k1KeyPair: KeyPair = createSecp256k1KeyPair(SecureRandomUtils.secureRandom)

  @throws[NoSuchProviderException]
  @throws[NoSuchAlgorithmException]
  @throws[InvalidAlgorithmParameterException]
  def createSecp256k1KeyPair(random: SecureRandom): KeyPair =
    val keyPairGenerator = KeyPairGenerator.getInstance("ECDSA", "BC")
    val ecGenParameterSpec = new ECGenParameterSpec("secp256k1")
    keyPairGenerator.initialize(ecGenParameterSpec, random)
    keyPairGenerator.generateKeyPair
  end createSecp256k1KeyPair

  @throws[InvalidAlgorithmParameterException]
  @throws[NoSuchAlgorithmException]
  @throws[NoSuchProviderException]
  def createEcKeyPair: ECKeyPair = createEcKeyPair(SecureRandomUtils.secureRandom)

  @throws[InvalidAlgorithmParameterException]
  @throws[NoSuchAlgorithmException]
  @throws[NoSuchProviderException]
  def createEcKeyPair(random: SecureRandom): ECKeyPair = ECKeyPair.create(createSecp256k1KeyPair(random))

  def getAddress(ecKeyPair: ECKeyPair): String = getAddress(ecKeyPair.publicKey)

  def getAddress(publicKey: BigInt): String =
    getAddress(Numeric.toHexStringWithPrefixZeroPadded(publicKey, PUBLIC_KEY_LENGTH_IN_HEX))

  def getAddress(publicKey: String): String =
    var publicKeyNoPrefix = Numeric.cleanHexPrefix(publicKey)
    if (publicKeyNoPrefix.length < PUBLIC_KEY_LENGTH_IN_HEX) publicKeyNoPrefix = "0" * (PUBLIC_KEY_LENGTH_IN_HEX - publicKeyNoPrefix.length) + publicKeyNoPrefix
    val hash = Hash.sha3(publicKeyNoPrefix)
    hash.substring(hash.length - ADDRESS_LENGTH_IN_HEX) // right most 160 bits
  end getAddress


  def getAddress(publicKey: Array[Byte]): Array[Byte] = Hash.sha3(publicKey).takeRight(20)

  /**
   * Checksum address encoding as per <a
   * href="https://github.com/ethereum/EIPs/blob/master/EIPS/eip-55.md">EIP-55</a>.
   *
   * @param address a valid hex encoded address
   * @return hex encoded checksum address
   */
  def toChecksumAddress(address: String): String =
    val lowercaseAddress = Numeric.cleanHexPrefix(address).toLowerCase
    val addressHash = Numeric.cleanHexPrefix(Hash.sha3String(lowercaseAddress))
    val result = new StringBuilder(lowercaseAddress.length + 2)
    result.append("0x")
    for (i <- 0 until lowercaseAddress.length) {
      if (Integer.parseInt(String.valueOf(addressHash.charAt(i)), 16) >= 8) result.append(String.valueOf(lowercaseAddress.charAt(i)).toUpperCase)
      else result.append(lowercaseAddress.charAt(i))
    }
    result.toString
  end toChecksumAddress


  def serialize(ecKeyPair: ECKeyPair): Array[Byte] =
    val privateKey = Numeric.toBytesPadded(ecKeyPair.privateKey, PRIVATE_KEY_SIZE)
    val publicKey = Numeric.toBytesPadded(ecKeyPair.publicKey, PUBLIC_KEY_SIZE)
    val result = new Array[Byte](PRIVATE_KEY_SIZE + PUBLIC_KEY_SIZE)
    Array.copy(privateKey, 0, result, 0, PRIVATE_KEY_SIZE)
    Array.copy(publicKey, 0, result, PRIVATE_KEY_SIZE, PUBLIC_KEY_SIZE)
    result
  end serialize

  def deserialize(input: Array[Byte]): ECKeyPair =
    if (input.length != PRIVATE_KEY_SIZE + PUBLIC_KEY_SIZE) throw new RuntimeException("Invalid input key size")
    val privateKey = Numeric.toBigInt(input, 0, PRIVATE_KEY_SIZE)
    val publicKey = Numeric.toBigInt(input, PRIVATE_KEY_SIZE, PUBLIC_KEY_SIZE)
    ECKeyPair(privateKey, publicKey)
  end deserialize

end Keys

