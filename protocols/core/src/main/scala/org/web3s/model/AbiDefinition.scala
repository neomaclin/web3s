package org.web3s.model

final case class AbiDefinition(constant: Boolean,
                                inputs: List[AbiDefinition.NamedType],
                                name: String,
                                outputs: List[AbiDefinition.NamedType],
                                `type`: String,
                                payable: Boolean,
                                stateMutability: StateMutability)

object AbiDefinition:
  import io.circe.Decoder
  import io.circe.syntax._
  import io.circe.generic.semiauto._
  given Decoder[NamedType] = deriveDecoder[NamedType]
  given Decoder[StateMutability] = deriveDecoder[StateMutability]

  final case class NamedType(
                              name: String,
                              `type`: String,
                              components: List[io.circe.Json],
                              internalType: String,
                              indexed: Boolean
                            ):
    def isDynamic: Boolean = `type` match
      case "string" | "bytes" | "[]" => true
      case _ => components.exists(_.as[NamedType].toOption.get.isDynamic)

    def nestedness: Int = components.map(_.as[NamedType].toOption.get.nestedness).sum

    def structIdentifier: Int =
      val typeStr = if internalType.isEmpty then `type` else internalType
      val subStructIdentifier = components.view.map(_.as[NamedType].toOption.get.structIdentifier).map(String.valueOf).mkString
      (typeStr ++ subStructIdentifier).hashCode