
package org.web3s.crypto

import org.bouncycastle.math.ec.ECPoint
import org.web3s.utils.Numeric
import java.nio.ByteBuffer
import java.util
import org.web3s.crypto.Hash.{hmacSha512, sha256hash160}

object Bip32ECKeyPair:
  val HARDENED_BIT = 0x80000000

  def create(privateKey: BigInt, chainCode: Array[Byte]): Bip32ECKeyPair =
    Bip32ECKeyPair(Some(privateKey), Sign.publicKeyFromPrivate(privateKey), 0, chainCode, None)

  def create(privateKey: Array[Byte], chainCode: Array[Byte]): Bip32ECKeyPair =
    create(Numeric.toBigInt(privateKey), chainCode)

  def generateKeyPair(seed: Array[Byte]): Bip32ECKeyPair =
    val (il, ir) = hmacSha512("Bitcoin seed".getBytes, seed).splitAt(32)
    Bip32ECKeyPair.create(il, ir.take(32))

  def deriveKeyPair(master: Bip32ECKeyPair, path: Array[Int]): Bip32ECKeyPair =
    path.foldLeft(master)(_.deriveChildKey(_))

  private def bigIntegerToBytes32(b: BigInt): Array[Byte] =
    val numBytes = 32
    val src = b.toByteArray
    val dest = new Array[Byte](numBytes)
    val isFirstByteOnlyForSign = src(0) == 0
    val length = if (isFirstByteOnlyForSign) src.length - 1 else src.length
    val srcPos = if (isFirstByteOnlyForSign) 1 else 0
    val destPos = numBytes - length
    Array.copy(src, srcPos, dest, destPos, length)
    dest
  end bigIntegerToBytes32

  private def isHardened(a: Int) = (a & HARDENED_BIT) != 0

end Bip32ECKeyPair


final case class Bip32ECKeyPair(privateKey: Option[BigInt],
                                publicKey: BigInt,
                                childNumber: Int,
                                chainCode: Array[Byte],
                                parent: Option[Bip32ECKeyPair]) :
  
  private def hasPrivateKey = privateKey.nonEmpty || parentHasPrivate

  private def parentHasPrivate: Boolean = parent.nonEmpty && parent.exists(_.hasPrivateKey)

  private def deriveChildKey(childNumber: Int): Bip32ECKeyPair =
    
    if (!hasPrivateKey) 
      val parentPublicKey = publicKeyPoint.getEncoded(true)
      val data = ByteBuffer.allocate(37)
      data.put(parentPublicKey)
      data.putInt(childNumber)
      val i = hmacSha512(chainCode, data.array)
      val il = i.slice(0, 32)
      val chainCodeLocal = i.slice(32, 64)
      val ilInt = BigInt(1, il)
      val ki = Sign.publicPointFromPrivate(ilInt).add(publicKeyPoint)
      Bip32ECKeyPair(None, Sign.publicFromPoint(ki.getEncoded(true)), childNumber, chainCodeLocal, Some(this))
    else
      val data = ByteBuffer.allocate(37)
      if (Bip32ECKeyPair.isHardened(childNumber))
        data.put(privateKeyBytes33)
      else
        data.put(publicKeyPoint.getEncoded(true))
      end if
      data.putInt(childNumber)
      val i = hmacSha512(chainCode, data.array)
      val il = i.slice(0, 32)
      val chainCodeLocal = i.slice(32, 64)
      val ilInt = BigInt(1, il)
      val privateKey1 = privateKey.map(i => (i + ilInt) % Sign.CURVE.getN)
      Bip32ECKeyPair(privateKey1, Sign.publicKeyFromPrivate(privateKey1.getOrElse(BigInt(0))), childNumber, chainCodeLocal, Some(this))
    
  end deriveChildKey

  private def identifier = sha256hash160(publicKeyPoint.getEncoded(true))

  private def fingerprint: Int =
    val id = identifier
    id(3) & 0xFF | (id(2) & 0xFF) << 8 | (id(1) & 0xFF) << 16 | (id(0) & 0xFF) << 24
  end fingerprint
  
  def parentFingerprint: Option[Int] = parent.map(_.fingerprint)
  
  def publicKeyPoint: ECPoint = Sign.publicPointFromPrivate(privateKey.getOrElse(BigInt(0)))

  def privateKeyBytes33: Array[Byte] =
    val numBytes = 33
    val bytes33 = new Array[Byte](numBytes)
    val priv = Bip32ECKeyPair.bigIntegerToBytes32(privateKey.getOrElse(BigInt(0)))
    Array.copy(priv, 0, bytes33, numBytes - priv.length, priv.length)
    bytes33
  end privateKeyBytes33
  
  def depth: Int = parent.fold(0)(_.depth + 1)


  def toECKeyPair: ECKeyPair = ECKeyPair(privateKey = privateKey.getOrElse(BigInt(0)), publicKey = publicKey)
end Bip32ECKeyPair
