package org.web3s.crypto.transaction

object RawTransaction:
  def createContractTransaction(nonce: BigInt,
                                gasPrice: BigInt,
                                gasLimit: BigInt,
                                value: BigInt,
                                init: String): RawTransaction =
    new RawTransaction(LegacyTransaction.createContractTransaction(nonce, gasPrice, gasLimit, value, init))

  def createEtherTransaction(nonce: BigInt,
                             gasPrice: BigInt,
                             gasLimit: BigInt,
                             to: String,
                             value: BigInt): RawTransaction =
    new RawTransaction(LegacyTransaction.createEtherTransaction(nonce, gasPrice, gasLimit, to, value))

  def createEtherTransaction(chainId: Long,
                             nonce: BigInt,
                             gasLimit: BigInt,
                             to: String,
                             value: BigInt,
                             maxPriorityFeePerGas: BigInt,
                             maxFeePerGas: BigInt): RawTransaction =
    new RawTransaction(Transaction1559.createEtherTransaction(chainId, nonce, gasLimit, to, value, maxPriorityFeePerGas, maxFeePerGas))

  def createTransaction(nonce: BigInt,
                        gasPrice: BigInt,
                        gasLimit: BigInt,
                        to: String,
                        data: String): RawTransaction =
    createTransaction(nonce, gasPrice, gasLimit, to, BigInt(0), data)

  def createTransaction(nonce: BigInt,
                        gasPrice: BigInt,
                        gasLimit: BigInt,
                        to: String,
                        value: BigInt,
                        data: String): RawTransaction =
    new RawTransaction(LegacyTransaction.createTransaction(nonce, gasPrice, gasLimit, to, value, data))

  def createTransaction(chainId: Long,
                        nonce: BigInt,
                        gasLimit: BigInt,
                        to: String,
                        value: BigInt,
                        data: String,
                        maxPriorityFeePerGas: BigInt,
                        maxFeePerGas: BigInt): RawTransaction =
    new RawTransaction(Transaction1559.createTransaction(chainId, nonce, gasLimit, to, value, data, maxPriorityFeePerGas, maxFeePerGas))

end RawTransaction


class RawTransaction(val transaction: Transaction):
  
  def this(nonce: BigInt,
           gasPrice: BigInt,
           gasLimit: BigInt,
           to: String,
           value: BigInt,
           data: String) =
    this(LegacyTransaction(nonce, gasPrice, gasLimit, to, value, data))
    
end RawTransaction

