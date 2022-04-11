
package org.web3s.crypto

import org.bouncycastle.crypto.digests.SHA512Digest
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator
import org.bouncycastle.crypto.params.KeyParameter

import scala.io.Source
import scala.util.Using
import org.web3s.crypto.Hash.sha256
import sun.nio.cs.UTF_8

import scala.collection.BitSet


object MnemonicUtils:
  private val SEED_ITERATIONS = 2048
  private val SEED_KEY_SIZE = 512

  def generateMnemonic(initialEntropy: Array[Byte]): String =
    validateEntropy(initialEntropy)

    val words = getWords
    val ent = initialEntropy.length * 8
    val checksumLength = ent / 32
    val checksum = calculateChecksum(initialEntropy)
    val bits = convertToBits(initialEntropy, checksum)
    val iterations = (ent + checksumLength) / 11

    (0 until iterations).view
      .map(i => toInt(nextElevenBits(bits, i)))
      .map(i => words(i)).mkString(" ")

  end generateMnemonic


  /**
   * Create entropy from the mnemonic.
   *
   * @param mnemonic The input mnemonic which should be 128-160 bits in length containing only
   *                 valid words
   * @return Byte array representation of the entropy
   */
  def generateEntropy(mnemonic: String): Array[Byte] = {
   // val bits = BitSet.empty
    val (size,bits) = mnemonicToBits(mnemonic)
    if (size == 0) throw new IllegalArgumentException("Empty mnemonic")
    val ent = 32 * size / 33
    if (ent % 8 != 0) throw new IllegalArgumentException("Wrong mnemonic size")
    val entropy = new Array[Byte](ent / 8)
     entropy.indices foreach (i => entropy(i) = readByte(bits, i))
    validateEntropy(entropy)
    val expectedChecksum = calculateChecksum(entropy)
    val actualChecksum = readByte(bits, entropy.length)
    if (expectedChecksum != actualChecksum) throw new IllegalArgumentException("Wrong checksum")
    entropy
  }

  def getWords: Seq[String] = populateWordList

  def generateSeed(mnemonic: String, passphraseOption: Option[String]): Array[Byte] = {
    if (isMnemonicEmpty(mnemonic)) throw new IllegalArgumentException("Mnemonic is required to generate a seed")
    val passphrase = passphraseOption.getOrElse("")
    val salt = String.format("mnemonic%s", passphrase)
    val gen = new PKCS5S2ParametersGenerator(new SHA512Digest)
    gen.init(mnemonic.getBytes, salt.getBytes, SEED_ITERATIONS)
    gen.generateDerivedParameters(SEED_KEY_SIZE).asInstanceOf[KeyParameter].getKey
  }

  def validateMnemonic(mnemonic: String): Boolean = try {
    generateEntropy(mnemonic)
    true
  } catch {
    case ex: Exception =>
      false
  }

  private def isMnemonicEmpty(mnemonic: String) = mnemonic == null || mnemonic.trim.isEmpty

  private def nextElevenBits(bits: Array[Boolean], i: Int) =
    val from = i * 11
    val to = from + 11
    bits.slice(from, to)
  end nextElevenBits


  private def validateEntropy(entropy: Array[Byte]): Unit =
    if (entropy.isEmpty) throw new IllegalArgumentException("Entropy is required")
    val ent = entropy.length * 8
    if (ent < 128 || ent > 256 || ent % 32 != 0) throw new IllegalArgumentException("The allowed size of ENT is 128-256 bits of " + "multiples of 32")
  end validateEntropy

  private def convertToBits(initialEntropy: Array[Byte], checksum: Byte):Array[Boolean] =
    val ent = initialEntropy.length * 8
    val checksumLength = ent / 32
    val totalLength = ent + checksumLength
    val bits = new Array[Boolean](totalLength)

    for
      i <- initialEntropy.indices
      j <- 0 until 8
    do
      bits(8 * i + j) = toBit(initialEntropy(i), j)
    end for

    for
      i <- 0 until checksumLength
    do
      bits(ent + i) = toBit(checksum, i)
    end for

    bits
  end convertToBits


  private def toBit(value: Byte, index: Int) = ((value >>> (7 - index)) & 1) > 0

  private def toInt(bits: Array[Boolean]):Int =
    var value = 0
    for i <- bits.indices do if (bits(i)) value += 1 << bits.length - i - 1
    value
  end toInt


  private def mnemonicToBits(mnemonic: String):(Int, java.util.BitSet) =
    var bit = 0
    val bitSet = new java.util.BitSet
    val vocabulary = getWords
    val tokenizer = new java.util.StringTokenizer(mnemonic, " ")
    while
      tokenizer.hasMoreTokens
    do
      val word = tokenizer.nextToken
      val index = vocabulary.indexOf(word)
      if (index < 0) throw new IllegalArgumentException(String.format("Mnemonic word '%s' should be in the word list", word))
      for
        k <- 0 until 11
      do
        bitSet.set(bit,isBitSet(index, 10 - k))
        bit += 1
      end for

    (bit, bitSet)
  end mnemonicToBits


  private def readByte(bits: java.util.BitSet, startByte: Int):Byte = {
    var res:Byte = 0
    for
      k <- 0 until 8
    do if (bits.get(startByte * 8 + k)) res = (res | (1 << (7 - k))).toByte
    res
  }

  private def isBitSet(n: Int, k: Int): Boolean = ((n >> k) & 1) == 1

  def calculateChecksum(initialEntropy: Array[Byte]): Byte =
    val ent = initialEntropy.length * 8
    val mask = (0xff << 8 - ent / 32).toByte
    val bytes = sha256(initialEntropy)
    (bytes(0) & mask).toByte
  end calculateChecksum

  private def populateWordList: List[String] =
    Using.resource(Source.fromResource("en-mnemonic-word-list.txt"))(_.getLines.toList)

end MnemonicUtils
