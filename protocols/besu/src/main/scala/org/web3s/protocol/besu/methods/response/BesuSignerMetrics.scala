package org.web3s.protocol.besu.methods.response

import org.web3s.protocol.core.Response
import org.web3s.utils.EthBigInt

opaque type BesuSignerMetrics = Response[List[BesuSignerMetrics.BesuSignerMetric]]

object BesuSignerMetrics:
  
  final case class BesuSignerMetric(address: String,
                                    proposedBlockCount: EthBigInt,
                                    lastProposedBlockNumber: EthBigInt
                                   )

  def apply(responses: Response[List[BesuSignerMetric]]): BesuSignerMetrics = responses

extension (x: BesuSignerMetrics)
  def signerMetrics: List[BesuSignerMetrics.BesuSignerMetric] = x.result

