package org.web3s.protocol.eea.util

import Base64String.*
import cats.Eq
import cats.syntax.eq.*

package codecs:


  given io.circe.Encoder[Base64String] = io.circe.Encoder.encodeString.contramap(_.asString)
  given io.circe.Decoder[Base64String] = io.circe.Decoder.decodeString.map(Base64String.wrap)
  given io.circe.Decoder[Restriction] = io.circe.Decoder.decodeString.map(Restriction.fromString)

  given Eq[Array[Byte]] = Eq.instance(_ sameElements _)

  given Eq[Base64String] = Eq.instance(_.asString == _.asString)

  given Eq[List[Base64String] | Base64String] = Eq.instance[List[Base64String] | Base64String] {
    case (list1: List[Base64String], list2: List[Base64String]) => Eq[List[Base64String]].eqv(list1, list2)
    case (item1: Base64String, item2: Base64String) => Eq[Base64String].eqv(item1, item2)
    case (_,_) => false
  }
