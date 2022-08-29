package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.utils.Numeric

opaque type EthBlock = Response[Option[EthBlock.Block]]

object EthBlock:
  import EthTransaction._

  type TransactionResult = Transaction | String
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
                          transactions: List[TransactionResult],
                          uncles: List[String],
                          sealFields: List[String],
                          baseFeePerGas: String
                        )
  def apply(responses: Response[Option[Block]]): EthBlock = responses

extension (x: EthBlock)
  def block: Option[EthBlock.Block] = x.result
