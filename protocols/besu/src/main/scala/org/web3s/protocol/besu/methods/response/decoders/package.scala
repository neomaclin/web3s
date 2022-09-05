package org.web3s.protocol.besu.methods.response


import org.web3s.protocol.eea.util.Base64String
import org.web3s.protocol.eea.util.codecs.given
import io.circe.Decoder
import io.circe.syntax._
import io.circe.generic.semiauto._
import cats.syntax.functor._
import model.*

package decoders:

  given Decoder[PrivacyGroupType] = Decoder.decodeString.map(PrivacyGroupType.valueOf)

  given Decoder[List[Base64String] | Base64String] = Decoder[List[Base64String]].widen or Decoder[Base64String].widen

  given Decoder[PrivateTransaction] = deriveDecoder[PrivateTransaction]
