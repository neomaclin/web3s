
package org.web3s.crypto.transaction

import org.web3s.crypto.Sign
import org.web3s.rlp.{RlpString, RlpType}
import org.web3s.utils.{Bytes, Numeric}

object LegacyTransaction:

  def createContractTransaction(nonce: BigInt,
                                gasPrice: BigInt,
                                gasLimit: BigInt,
                                value: BigInt,
                                init: String): LegacyTransaction =
    LegacyTransaction(nonce, gasPrice, gasLimit, "", value, init)

  def createEtherTransaction(nonce: BigInt,
                             gasPrice: BigInt,
                             gasLimit: BigInt,
                             to: String,
                             value: BigInt): LegacyTransaction =
    LegacyTransaction(nonce, gasPrice, gasLimit, to, value, "")

  def createTransaction(nonce: BigInt,
                        gasPrice: BigInt,
                        gasLimit: BigInt,
                        to: String,
                        data: String): LegacyTransaction =
    createTransaction(nonce, gasPrice, gasLimit, to, BigInt(0), data)

  def createTransaction(nonce: BigInt,
                        gasPrice: BigInt,
                        gasLimit: BigInt,
                        to: String,
                        value: BigInt,
                        data: String): LegacyTransaction =
    LegacyTransaction(nonce, gasPrice, gasLimit, to, value, Numeric.cleanHexPrefix(data))

end LegacyTransaction


final case class LegacyTransaction(nonce: BigInt,
                                   gasPrice: BigInt,
                                   gasLimit: BigInt,
                                   to: String,
                                   value: BigInt,
                                   data: String) extends Transaction :

  def asRlpValues(signatureData: Sign.SignatureData): List[RlpType] =
    var result = List.empty[RlpType]

    result = RlpString.from(nonce) :: result
    result = RlpString.from(gasPrice) :: result
    result = RlpString.from(gasLimit) :: result
    result = (if (to.isEmpty) RlpString.from("") else RlpString(Numeric.hexStringToByteArray(to))) :: result
    result = RlpString.from(value) :: result
    result = RlpString(Numeric.hexStringToByteArray(data)) :: result
    if (signatureData.nonEmpty)
      result = RlpString(Bytes.trimLeadingZeroes(signatureData.v)) :: result
      result = RlpString(Bytes.trimLeadingZeroes(signatureData.r)) :: result
      result = RlpString(Bytes.trimLeadingZeroes(signatureData.s)) :: result
    end if
    
    result.reverse

  end asRlpValues

  override def `type`: Transaction.Type = Transaction.Type.LEGACY

end LegacyTransaction