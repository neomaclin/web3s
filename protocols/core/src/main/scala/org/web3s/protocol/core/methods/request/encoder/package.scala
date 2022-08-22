package org.web3s.protocol.core.methods.request

import io.circe.Encoder
import io.circe.syntax._
import io.circe.generic.semiauto._
//import io.circe.generic._
import org.web3s.utils.Numeric
import org.web3s.protocol.core.*
import org.web3s.protocol.core.methods.request.*
package encoder:

  given Encoder[NullTopic.type] = Encoder.instance(_ => io.circe.Json.Null)
  given Encoder[SingleTopic] =  Encoder.encodeString.contramap(_.value)

  given Encoder[NullTopic.type | SingleTopic] = Encoder.instance{
    case n@NullTopic => io.circe.Json.Null
    case s:SingleTopic => s.value.asJson
  }
  given Encoder[ListTopic] = Encoder.encodeList[NullTopic.type  | SingleTopic].contramap(_.value)
  given Encoder[FilterTopic[_]] = Encoder.instance {
    case n@NullTopic => io.circe.Json.Null
    case s: SingleTopic => s.value.asJson
    case l: ListTopic => l.asJson
  }

  given Encoder[Option[List[String]]] = Encoder.instance{ a =>
     a.toList.flatten match
       case Nil => io.circe.Json.Null
       case all => all.asJson
  }
  given Encoder[BigInt] = Encoder.encodeString.contramap(Numeric.encodeQuantity)
  given Encoder[DefaultBlockParameterNumber] = Encoder.encodeString.contramap(_.value)
  given Encoder[DefaultBlockParameterName] = Encoder.encodeString.contramap(_.value)
  given Encoder[DefaultBlockParameter] = Encoder.instance {
    case number: DefaultBlockParameterNumber => number.asJson
    case name: DefaultBlockParameterName => name.asJson
  }

  given Encoder[Transaction] = deriveEncoder[Transaction]

