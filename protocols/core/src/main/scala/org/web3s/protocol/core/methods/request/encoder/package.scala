package org.web3s.protocol.core.methods.request

import io.circe.Encoder
import io.circe.syntax._
import io.circe.generic.semiauto._
//import io.circe.generic._
import org.web3s.utils.Numeric
import org.web3s.protocol.core.*

package encoder:



  given Encoder[BigInt] = Encoder.encodeString.contramap(Numeric.encodeQuantity)
  given Encoder[DefaultBlockParameterNumber] = Encoder.encodeString.contramap(_.value)
  given Encoder[DefaultBlockParameterName] = Encoder.encodeString.contramap(_.value)
  given Encoder[DefaultBlockParameter] = Encoder.instance {
    case number: DefaultBlockParameterNumber => number.asJson
    case name: DefaultBlockParameterName => name.asJson
  }

  given Encoder[Transaction] = deriveEncoder[Transaction]
