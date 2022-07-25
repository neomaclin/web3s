package org.web3s.protocol.besu.methods.response

import org.web3s.protocol.core.Response
import org.web3s.protocol.core.Response.EthBigInt

opaque type BesuSignerMetrics = Response[List[BesuSignerMetric]]

object BesuSignerMetrics:
  
  final case class BesuSignerMetric(address: String,
                                    proposedBlockCount: EthBigInt,
                                    lastProposedBlockNumber: EthBigInt
                                   )
  def apply(response: Response[List[BesuSignerMetric]]): BesuEthAccountsMapResponse = response

extension (x: BesuSignerMetrics)
  def signerMetrics: List[BesuSignerMetric] = x.result

