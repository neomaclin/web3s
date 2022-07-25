package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.utils.EthBigInt
import org.web3s.utils.Numeric

opaque type EthFeeHistory = Response[EthFeeHistory.FeeHistory]

object EthFeeHistory:

  extension (x: EthFeeHistory)
    def feeHistory: EthFeeHistory.FeeHistory = x.result

  final case class FeeHistory(oldestBlock: String,
                              reward: List[List[EthBigInt]],
                              baseFeePerGas: List[EthBigInt],
                              gasUsedRatio: List[Double]
                             )

  def apply(responses: Response[FeeHistory]): EthFeeHistory = responses


