package org.web3s.protocol.core.methods.response


import org.web3s.protocol.core.Response
import org.web3s.utils.Numeric
import org.web3s.crypto.transaction.{ Transaction => TransactionUtil }

opaque type EthTransaction = Response[Option[EthTransaction.Transaction]]

object EthTransaction:

  final case class AccessListObject(address: String, storageKeys: List[String])

  final case class Transaction(
                                hash: String,
                                nonce: String,
                                blockHash: String,
                                blockNumber: String,
                                chainId: Option[String] = None,
                                transactionIndex: String,
                                from: String,
                                to: String,
                                value: String,
                                gas: String,
                                gasPrice: String,
                                input: String,
                                creates: Option[String],
                                publicKey: String,
                                raw: String,
                                r: String,
                                s: String,
                                v: Long,
                                `type`: String,
                                maxFeePerGas: String,
                                maxPriorityFeePerGas: String,
                                accessList: List[AccessListObject]
                              ) {
    def chainIdEncoded: Long = chainId.map(Numeric.decodeQuantity(_).longValue).getOrElse(TransactionUtil.deriveChainId(v))
  }

  def apply(response: Response[Option[Transaction]]): EthTransaction = response

extension (x: EthTransaction)
  def transaction: Option[EthTransaction.Transaction] = x.result
