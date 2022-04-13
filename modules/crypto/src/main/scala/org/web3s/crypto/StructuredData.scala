package org.web3s.crypto

import eu.timepit.refined.api.Refined
import eu.timepit.refined.collection.NonEmpty
import eu.timepit.refined.string.*

import org.web3s.abi.datatypes.{Address, SolidityUInt}
import org.web3s.abi.datatypes.generated.UInt256

import io.circe._
import io.circe.refined._
import io.circe.generic.semiauto._

import scala.util.matching.Regex

object StructuredData:
  type TypeStringSpec = MatchesRegex["""^[a-zA-Z_$][a-zA-Z_$0-9]*(\\[([1-9]\\d*)*\\])*$"""]
  type IdentifierStringSpec = MatchesRegex["""^[a-zA-Z_$][a-zA-Z_$0-9]*$"""]

  final case class Entry(name: Refined[String, IdentifierStringSpec],
                         `type`: Refined[String, TypeStringSpec])

  final case class EIP712Domain(name: String,
                                version: String,
                                chainId: Option[UInt256],
                                verifyingContract: Address,
                                salt: Option[String])

  final case class EIP712Message(types: Map[String, List[StructuredData.Entry]],
                                 primaryType: String,
                                 message: io.circe.Json,
                                 domain: StructuredData.EIP712Domain)


  given io.circe.Decoder[UInt256] = Decoder.decodeInt.map(s => new UInt256(BigInt(s)))

  given io.circe.Decoder[Address] = Decoder.decodeString.map(s => new Address(s))

  given io.circe.Decoder[Entry] = deriveDecoder

  given io.circe.Decoder[EIP712Message] = deriveDecoder

end StructuredData


