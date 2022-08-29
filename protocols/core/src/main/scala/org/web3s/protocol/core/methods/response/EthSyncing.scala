package org.web3s.protocol.core.methods.response

import org.web3s.protocol.core.Response
import org.web3s.protocol.core.methods.response.EthSyncing.Result


opaque type EthSyncing = Response[EthSyncing.Result | Boolean]

object EthSyncing:
  final case class Result(startingBlock: Option[String],
                          currentBlock: Option[String],
                          highestBlock: Option[String],
                          knownStates: Option[String],
                          pulledStates: Option[String])

  def apply(response: Response[Result | Boolean]): EthSyncing = response

extension (x: EthSyncing)
  def isSyncing: Boolean = x.result match
    case result: EthSyncing.Result => false
    case value: Boolean => value

  def result: Result = x.result match
    case result: EthSyncing.Result => result
    case value => Result(None,None,None,None,None)

