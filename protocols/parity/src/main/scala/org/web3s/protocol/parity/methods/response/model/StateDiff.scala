package org.web3s.protocol.parity.methods.response.model

object StateDiff:
  enum State(val isChanged: Boolean):
    case ChangedState(from: String, to: String) extends State(true)
    case UnchangedState extends State(false)
    case AddedState(value: String) extends State(true)

final case class StateDiff(balance: StateDiff.State,
                           code: StateDiff.State,
                           nonce: StateDiff.State,
                           storage: Map[String, StateDiff.State])


