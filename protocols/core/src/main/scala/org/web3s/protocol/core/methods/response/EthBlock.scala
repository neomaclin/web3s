package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.utils.Numeric

opaque type EthBlock = Response[EthBlock.Block]

object EthBlock:
  import io.circe.Decoder
  import io.circe.syntax._
  import io.circe.generic.semiauto._
  import EthTransaction._

  type TransactionResult = Transaction

  given Decoder[Block] = deriveDecoder[Block]

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
  def apply(responses: Response[Block]): EthBlock = responses

extension (x: EthBlock)
  def block: EthBlock.Block = x.result
