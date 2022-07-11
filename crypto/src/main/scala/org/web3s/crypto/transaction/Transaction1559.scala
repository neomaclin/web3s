package org.web3s.crypto.transaction

import org.web3s.rlp.{RlpList, RlpString, RlpType}
import org.web3s.utils.{Bytes, Numeric}
import org.web3s.crypto.Sign

object Transaction1559:

  def createEtherTransaction(chainId: Long,
                             nonce: BigInt,
                             gasLimit: BigInt,
                             to: String,
                             value: BigInt,
                             maxPriorityFeePerGas: BigInt,
                             maxFeePerGas: BigInt):Transaction1559 =
     Transaction1559(chainId, nonce, gasLimit, to, value, "", maxPriorityFeePerGas, maxFeePerGas)

  def createTransaction(chainId: Long,
                        nonce: BigInt,
                        gasLimit: BigInt,
                        to: String,
                        value: BigInt,
                        data: String,
                        maxPriorityFeePerGas: BigInt,
                        maxFeePerGas: BigInt):Transaction1559 =
     Transaction1559(chainId, nonce, gasLimit, to, value, Numeric.cleanHexPrefix(data), maxPriorityFeePerGas, maxFeePerGas)

end Transaction1559


final case class Transaction1559(chainId: Long,
                                 nonce: BigInt,
                                 gasLimit: BigInt,
                                 to: String,
                                 value: BigInt,
                                 data: String,
                                 maxPriorityFeePerGas: BigInt,
                                 maxFeePerGas: BigInt) extends Transaction :

  override def asRlpValues(signatureData: Sign.SignatureData): List[RlpType] =
    var result = List.empty[RlpType]
    
    result = RlpString.from(chainId) :: result
    result = RlpString.from(nonce) :: result
    result = RlpString.from(maxPriorityFeePerGas) :: result
    result = RlpString.from(maxFeePerGas) :: result
    result = RlpString.from(gasLimit) :: result
    result = (if (to.isEmpty) RlpString.from("") else RlpString(Numeric.hexStringToByteArray(to))) :: result
    result = RlpString.from(value) :: result
    result = RlpString(Numeric.hexStringToByteArray(data)) :: result
    result = RlpList() :: result
    if (signatureData.nonEmpty)
      result = RlpString.from(Sign.getRecId(signatureData, chainId)) :: result
      result = RlpString(Bytes.trimLeadingZeroes(signatureData.r)) :: result
      result = RlpString(Bytes.trimLeadingZeroes(signatureData.s)) :: result

    result.reverse
  end asRlpValues

  override def gasPrice: BigInt = throw new UnsupportedOperationException("not available for 1559 transaction")

  override def `type`: Transaction.Type = Transaction.Type.EIP1559

end Transaction1559
