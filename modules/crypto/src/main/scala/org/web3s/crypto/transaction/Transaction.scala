package org.web3s.crypto.transaction

import org.web3s.crypto.*
import org.web3s.crypto.exception.CryptoWeb3sException
import org.web3s.rlp
import org.web3s.rlp.{RlpList, RlpString, RlpType}
import org.web3s.utils.Numeric

import java.nio.ByteBuffer

trait Transaction:

  def asRlpValues(signatureData: Sign.SignatureData): List[RlpType]

  def nonce: BigInt

  def gasPrice: BigInt

  def gasLimit: BigInt

  def to: String

  def value: BigInt

  def data: String

  def `type`: Transaction.Type

end Transaction

object Transaction:
  val CHAIN_ID_INC = 35
  val LOWER_REAL_V = 27

  enum Type(val rlpType: Byte):
    case LEGACY extends Type(0x00)
    case EIP1559 extends Type(0x02)

  object Encoder:

    def signMessage(rawTransaction: RawTransaction, credentials: Credentials): Array[Byte] =
      val encodedTransaction: Array[Byte] = encode(rawTransaction)
      val signatureData: Sign.SignatureData = Sign.signMessage(encodedTransaction, credentials.ecKeyPair)
      encode(rawTransaction, signatureData)
    end signMessage

    def signMessage(rawTransaction: RawTransaction, chainId: Long, credentials: Credentials): Array[Byte] =
      if (rawTransaction.transaction.`type` == Transaction.Type.EIP1559)
        signMessage(rawTransaction, credentials)
      else
        val encodedTransaction: Array[Byte] = encode(rawTransaction, chainId)
        val signatureData: Sign.SignatureData = Sign.signMessage(encodedTransaction, credentials.ecKeyPair)
        val eip155SignatureData: Sign.SignatureData = createEip155SignatureData(signatureData, chainId)
        encode(rawTransaction, eip155SignatureData)
    end signMessage

    def createEip155SignatureData(signatureData: Sign.SignatureData, chainId: Long): Sign.SignatureData =
      var v: BigInt = Numeric.toBigInt(signatureData.v)
      v = v - BigInt(LOWER_REAL_V)
      v = v + BigInt(chainId) * BigInt(2)
      v = v + BigInt(CHAIN_ID_INC)
      Sign.SignatureData(v.toByteArray, signatureData.r, signatureData.s)

    end createEip155SignatureData

    def encode(rawTransaction: RawTransaction): Array[Byte] = encode(rawTransaction,Sign.SignatureData(Array.emptyByteArray, Array.emptyByteArray, Array.emptyByteArray))

    def encode(rawTransaction: RawTransaction, chainId: Long): Array[Byte] =
      if (rawTransaction.transaction.`type` != Transaction.Type.LEGACY) {
        throw new CryptoWeb3sException("Incorrect transaction type. Tx type should be Legacy.")
      }
      val signatureData: Sign.SignatureData = Sign.SignatureData(longToBytes(chainId), Array.emptyByteArray, Array.emptyByteArray)
      encode(rawTransaction, signatureData)
    end encode

    def encode(rawTransaction: RawTransaction, signatureData: Sign.SignatureData): Array[Byte] =
      val rlpList = RlpList(asRlpValues(rawTransaction, signatureData).toVector)
      val encoded: Array[Byte] = rlp.Encoder.encode(rlpList)
      if (rawTransaction.transaction.`type` == Transaction.Type.EIP1559)
        ByteBuffer.allocate(encoded.length + 1).put(rawTransaction.transaction.`type`.rlpType).put(encoded).array
      else
        encoded
    end encode


    private def longToBytes(x: Long): Array[Byte] =
      val buffer: ByteBuffer = ByteBuffer.allocate(8)
      buffer.putLong(x)
      buffer.array
    end longToBytes

    def asRlpValues(rawTransaction: RawTransaction, signatureData: Sign.SignatureData): List[RlpType] =
      rawTransaction.transaction.asRlpValues(signatureData)

  end Encoder

  object Decoder:
    private val UNSIGNED_EIP1559TX_RLP_LIST_SIZE = 9

    def decode(hexTransaction: String): RawTransaction =
      val transaction = Numeric.hexStringToByteArray(hexTransaction)
      getTransactionType(transaction) match
        case Transaction.Type.EIP1559 => decodeEIP1559Transaction(transaction)
        case Transaction.Type.LEGACY => decodeLegacyTransaction(transaction)
    end decode

    private def getTransactionType(transaction: Array[Byte]): Transaction.Type = // The first byte indicates a transaction type.
      if (transaction.head == Transaction.Type.EIP1559.rlpType) Transaction.Type.EIP1559 else Transaction.Type.LEGACY

    private def decodeEIP1559Transaction(transaction: Array[Byte]) =
      val encodedTx = transaction.tail
      val rlpList = rlp.Decoder.decode(encodedTx)
      val values = rlpList.values(0).asInstanceOf[RlpList]
      val chainId = values.values(0).asInstanceOf[RlpString].asPositiveBigInt.longValue
      val nonce = values.values(1).asInstanceOf[RlpString].asPositiveBigInt
      val maxPriorityFeePerGas = values.values(2).asInstanceOf[RlpString].asPositiveBigInt
      val maxFeePerGas = values.values(3).asInstanceOf[RlpString].asPositiveBigInt
      val gasLimit = values.values(4).asInstanceOf[RlpString].asPositiveBigInt
      val to = values.values(5).asInstanceOf[RlpString].asString
      val value = values.values(6).asInstanceOf[RlpString].asPositiveBigInt
      val data = values.values(7).asInstanceOf[RlpString].asString
      val rawTransaction = org.web3s.crypto.transaction.RawTransaction.createTransaction(chainId, nonce, gasLimit, to, value, data, maxPriorityFeePerGas, maxFeePerGas)
      if (values.values.size == UNSIGNED_EIP1559TX_RLP_LIST_SIZE)
        rawTransaction
      else
        val v = Sign.getVFromRecId(Numeric.toBigInt(values.values(9).asInstanceOf[RlpString].value).intValue)
        val r = Numeric.toBytesPadded(Numeric.toBigInt(values.values(10).asInstanceOf[RlpString].value), 32)
        val s = Numeric.toBytesPadded(Numeric.toBigInt(values.values(11).asInstanceOf[RlpString].value), 32)
        val signatureData = Sign.SignatureData(v, r, s)
        new SignedRawTransaction(rawTransaction.transaction, signatureData)

    end decodeEIP1559Transaction


    private def decodeLegacyTransaction(transaction: Array[Byte]) =
      val rlpList = rlp.Decoder.decode(transaction)
      val values = rlpList.values(0).asInstanceOf[RlpList]
      val nonce = values.values(0).asInstanceOf[RlpString].asPositiveBigInt
      val gasPrice = values.values(1).asInstanceOf[RlpString].asPositiveBigInt
      val gasLimit = values.values(2).asInstanceOf[RlpString].asPositiveBigInt
      val to = values.values(3).asInstanceOf[RlpString].asString
      val value = values.values(4).asInstanceOf[RlpString].asPositiveBigInt
      val data = values.values(5).asInstanceOf[RlpString].asString
      if ((values.values.size == 6) || ((values.values.size == 8) &&
        (values.values(7).asInstanceOf[RlpString].value.length == 10)) || ((values.values.size == 9) &&
        (values.values(8).asInstanceOf[RlpString].value.length == 10))) // the 8th or 9nth element is the hex
      // representation of "restricted" for private transactions
        RawTransaction.createTransaction(nonce, gasPrice, gasLimit, to, value, data)
      else
        val v = values.values(6).asInstanceOf[RlpString].value
        val r = Numeric.toBytesPadded(Numeric.toBigInt(values.values(7).asInstanceOf[RlpString].value), 32)
        val s = Numeric.toBytesPadded(Numeric.toBigInt(values.values(8).asInstanceOf[RlpString].value), 32)
        val signatureData = Sign.SignatureData(v, r, s)
        new SignedRawTransaction(nonce, gasPrice, gasLimit, to, value, data, signatureData)
    end decodeLegacyTransaction


  end Decoder

  def generateTransactionHash(rawTransaction: RawTransaction, credentials: Credentials): Array[Byte] =
    Hash.sha3(Encoder.signMessage(rawTransaction, credentials))

  def generateTransactionHash(rawTransaction: RawTransaction, chainId: Byte, credentials: Credentials): Array[Byte] =
    Hash.sha3(Encoder.signMessage(rawTransaction, chainId, credentials))

  def generateTransactionHashHexEncoded(rawTransaction: RawTransaction, credentials: Credentials): String =
    Numeric.toHexString(generateTransactionHash(rawTransaction, credentials))

  def generateTransactionHashHexEncoded(rawTransaction: RawTransaction, chainId: Byte, credentials: Credentials): String =
    Numeric.toHexString(generateTransactionHash(rawTransaction, chainId, credentials))

  def deriveChainId(v: Long): Long =
    if (v == LOWER_REAL_V || v == (LOWER_REAL_V + 1)) 0L else (v - CHAIN_ID_INC) / 2

end Transaction
