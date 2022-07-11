package org.web3s.crypto.transaction
 
import org.web3s.crypto.Sign
import org.web3s.utils.Numeric

final class SignedRawTransaction(transaction: Transaction, override val signatureData: Sign.SignatureData)
  extends RawTransaction(transaction) 
    with SignatureDataOperations {

  def this(nonce: BigInt,
           gasPrice: BigInt,
           gasLimit: BigInt,
           to: String,
           value: BigInt,
           data: String,
           signatureData: Sign.SignatureData) =
    this(LegacyTransaction(nonce, gasPrice, gasLimit, to, value, Numeric.cleanHexPrefix(data)), signatureData)
    
  override def encodedTransaction(chainId: Option[Long]): Array[Byte] =
    chainId.fold(Transaction.Encoder.encode(this))(Transaction.Encoder.encode(this,_))

}