package org.web3s.protocol.core.methods.response


import org.web3s.protocol.core.Response


opaque type EthTransaction = Response[Option[EthTransaction.Transaction]]

object EthTransaction:
  
  final case class AccessListObject(address: String, storageKeys: List[String])

  final case class Transaction(
                                hash: String,
                                nonce: String,
                                blockHash: String,
                                blockNumber: String,
                                chainId: String,
                                transactionIndex: String,
                                from: String,
                                to: String,
                                value: String,
                                gas: String,
                                gasPrice: String,
                                input: String,
                                creates: String,
                                publicKey: String,
                                raw: String,
                                r: String,
                                s: String,
                                v: Long,
                                `type`: String,
                                maxFeePerGas: String,
                                maxPriorityFeePerGas: String,
                                accessList: List[AccessListObject]
                              )

  def apply(response: Response[Option[Transaction]]): EthTransaction = response

extension (x: EthTransaction)
  def transaction: Option[EthTransaction.Transaction] = x.result
