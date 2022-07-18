package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.utils.Numeric

opaque type EthBlock = Response[EthBlock.Block]

object EthBlock:
  type TransactionResult = EthTransaction.Transaction | String
  final case class Block(
                          number: String,
                          hash: String,
                          parentHash: String,
                          nonce: String,
                          sha3Uncles: String,
                          logsBloom: String,
                          transactionsRoot: String,
                          stateRoot: String,
                          receiptsRoot: String,
                          author: String,
                          miner: String,
                          mixHash: String,
                          difficulty: String,
                          totalDifficulty: String,
                          extraData: String,
                          size: String,
                          gasLimit: String,
                          gasUsed: String,
                          timestamp: String,
                          transactions: List[EthBlock.TransactionResult],
                          uncles: List[String],
                          sealFields: List[String],
                          baseFeePerGas: String
                        )
  def apply(response: Response[Block]): EthBlock = response

extension (x: EthBlock)
  def block: Block = x.result
