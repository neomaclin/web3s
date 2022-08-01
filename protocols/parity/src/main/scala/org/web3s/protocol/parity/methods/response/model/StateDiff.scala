package org.web3s.protocol.parity.methods.response.model

import io.circe.Decoder

object StateDiff:

  import io.circe.Decoder
  import io.circe.syntax._
  import io.circe.generic.auto._
  import io.circe.generic.semiauto

  given Decoder[StateDiff] = semiauto.deriveDecoder

  enum State(val isChanged: Boolean):
    case ChangedState(from: String, to: String) extends State(true)
    case UnchangedState(jsonString: String) extends State(false)
    case AddedState(value: String) extends State(true)

final case class StateDiff(balance: StateDiff.State,
                           code: StateDiff.State,
                           nonce: StateDiff.State,
                           storage: Map[String, StateDiff.State])


