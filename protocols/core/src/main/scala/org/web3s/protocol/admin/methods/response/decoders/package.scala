package org.web3s.protocol.admin.methods.response

import org.web3s.model.*
import io.circe.Decoder
import io.circe.generic.semiauto._
import cats.syntax.functor._
import org.web3s.protocol.core.methods.response.decoders.given

package decoders:

  given Decoder[TxPoolContent.TxPoolContentResult] = deriveDecoder[TxPoolContent.TxPoolContentResult]


