package org.web3s.crypto.transaction

import org.web3s.crypto.{Keys, Sign}
import org.web3s.crypto.transaction.SignatureDataOperations
import org.web3s.utils.Numeric

import java.security.SignatureException


trait SignatureDataOperations:
  
  def signatureData: Sign.SignatureData

  def encodedTransaction(chainId: Option[Long]): Array[Byte]

  @throws[SignatureException]
  def from: String =
    val v = Numeric.toBigInt(signatureData.v)
    val r = signatureData.r
    val s = signatureData.s
    val key = Sign.signedMessageToKey(encodedTransaction(chainId), Sign.SignatureData(Array[Byte](realV(v)), r, s))
    "0x" + Keys.getAddress(key)
  end from


  @throws[SignatureException]
  def verify(inFrom: String): Unit =
    if (inFrom != from) throw new SignatureException("from mismatch")
  end verify
  
  def realV(bv: BigInt): Byte =
    val v = bv.longValue
    if (v == Transaction.LOWER_REAL_V || v == (Transaction.LOWER_REAL_V + 1)) return v.toByte
    val realV = Transaction.LOWER_REAL_V
    var inc = 0
    if (v.toInt % 2 == 0) inc = 1
    (realV + inc).toByte
  end realV

  def chainId: Option[Long] =
    val v = Numeric.toBigInt(signatureData.v).longValue
    if (v == Transaction.LOWER_REAL_V || v == (Transaction.LOWER_REAL_V + 1)) None
    else Some((v - Transaction.CHAIN_ID_INC) / 2)
  end chainId

end SignatureDataOperations
