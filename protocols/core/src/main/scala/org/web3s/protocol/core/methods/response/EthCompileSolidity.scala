package org.web3s.protocol.core.methods.response

import org.web3s.model.AbiDefinition
import org.web3s.protocol.core.Response


opaque type EthCompileSolidity = Response[Map[String, EthCompileSolidity.Code]]

object EthCompileSolidity:

  import io.circe.Decoder
  import io.circe.generic.semiauto._
  import AbiDefinition._

  private given Decoder[Documentation] = deriveDecoder[Documentation]

  private given Decoder[AbiDefinition] = deriveDecoder[AbiDefinition]

  private given Decoder[SolidityInfo] = deriveDecoder[SolidityInfo]

  given Decoder[Code] = deriveDecoder[Code]

  def apply(responses: Response[Map[String, Code]]): EthCompileSolidity = responses

  final case class Code(code: String, info: SolidityInfo)

  final case class SolidityInfo(
                                 source: String,
                                 language: String,
                                 languageVersion: String,
                                 compilerVersion: String,
                                 compilerOptions: String,
                                 abiDefinition: List[AbiDefinition],
                                 userDoc: Documentation,
                                 developerDoc: Documentation
                               )

  final case class Documentation(methods: Map[String, String])

