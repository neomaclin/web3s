package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.utils.Numeric

opaque type EthFeeHistory = Response[EthFeeHistory.FeeHistory]

object EthFeeHistory:
  final case class FeeHistory(oldestBlock: String,
                              reward: List[List[String]],
                              baseFeePerGas: List[String],
                              gasUsedRatio: List[Double]
                             ):
    def rewardAsBigInt = reward.map(_.map(Numeric.decodeQuantity))
    def baseFeePerGasAsBigInt = baseFeePerGas.map(Numeric.decodeQuantity)

  def apply(responses: Response[FeeHistory]): EthFeeHistory = responses

extension (x: EthFeeHistory)
  def feeHistory: EthFeeHistory.FeeHistory = x.result
