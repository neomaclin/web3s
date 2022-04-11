package org.web3s.crypto

import eu.timepit.refined.api.Refined
import eu.timepit.refined.string._
import org.web3s.abi.datatypes.{Address, SolidityUInt}
import scala.util.matching.Regex

object StructuredData:
  type TypeStringSpec = MatchesRegex["""^[a-zA-Z_$][a-zA-Z_$0-9]*(\\[([1-9]\\d*)*\\])*$"""]
  type IdentifierStringSpec = MatchesRegex["""^[a-zA-Z_$][a-zA-Z_$0-9]*$"""]

  final case class Entry(name: String Refined IdentifierStringSpec,
                         `type`: String Refined TypeStringSpec)

  final case class EIP712Domain(name: String,
                                version: String,
                                chainId: SolidityUInt,
                                verifyingContract: Address,
                                salt: String)

  final case class EIP712Message(types: Map[String, List[StructuredData.Entry]],
                                 primaryType: String,
                                 message: String,
                                 domain: StructuredData.EIP712Domain)



end StructuredData


