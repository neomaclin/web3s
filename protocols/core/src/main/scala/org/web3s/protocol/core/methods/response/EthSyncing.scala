package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response


opaque type EthSyncing = Response[EthSyncing.Result]

object EthSyncing:
  final case class Result(isSyncing: Boolean = true,
                          startingBlock: Option[String],
                          currentBlock: Option[String],
                          highestBlock: Option[String],
                          knownStates: Option[String],
                          pulledStates: Option[String])

  def apply(response: Response[Result]): EthSyncing = response

extension (x: EthSyncing)
  def isSyncing: Boolean = x.result.isSyncing
