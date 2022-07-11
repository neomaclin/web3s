package org.web3s.protocol.core.methods.response

case class Transaction(
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
                        accessList: List[_]
                      )
