package org.web3s.ens

import org.web3s.utils.Numeric
import org.web3s.crypto.Hash

import scala.util.{Failure, Success, Try}
import java.util.Arrays
import java.net.IDN
import exceptions.EnsResolutionException

object NameHash:

  private val EMPTY = new Array[Byte](32)
  
  def nameHashAsBytes(ensName: String): Array[Byte]  = Numeric.hexStringToByteArray(nameHash(ensName))
  
  def nameHash(ensName: String): String = Numeric.toHexString(nameHash(normalise(ensName).split("\\.").toList))
  
  private def nameHash(labels: List[String]): Array[Byte] = 
    if labels.isEmpty || labels.head.isEmpty then EMPTY
    else 
      val tail = if labels.length == 1 then Nil else labels.tail
      val remainderHash = nameHash(tail)
      val result = Arrays.copyOf(remainderHash, 64)
      val labelHash = Hash.sha3(labels.head.getBytes)
      System.arraycopy(labelHash, 0, result, 32, labelHash.length)
      Hash.sha3(result)

  
  /**
   * Normalise ENS name as per the <a
   * href="http://docs.ens.domains/en/latest/implementers.html#normalising-and-validating-names">specification</a>.
   *
   * @param ensName our user input ENS name
   * @return normalised ens name
   * @throws EnsResolutionException if the name cannot be normalised
   */
  def normalise(ensName: String): String =
    Try(
      IDN.toASCII(ensName, IDN.USE_STD3_ASCII_RULES).toLowerCase
    ) match
      case Success(value) => value
      case Failure(_) => throw new EnsResolutionException("Invalid ENS name provided: " + ensName)



   
  /**
   * Encode Dns name. Reference implementation
   * https://github.com/ethers-io/ethers.js/blob/fc1e006575d59792fa97b4efb9ea2f8cca1944cf/packages/hash/src.ts/namehash.ts#L49
   *
   * @param name Dns name
   * @return Encoded name in Hex format.
   * @throws IOException
   */
  // @throws[IOException]
  // def dnsEncode(name: String) = {
  //   val parts = name.split("\\.")
  //   val outputStream = new ByteArrayOutputStream
  //   for (part <- parts) {
  //     val bytes = toUtf8Bytes("_" + normalise(part))
  //     if (bytes == null) break //todo: break is not supported
  //     bytes(0) = (bytes.length - 1).toByte
  //     outputStream.write(bytes)
  //   }
  //   Numeric.toHexString(outputStream.toByteArray) + "00"
  // }
