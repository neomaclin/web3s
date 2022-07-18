package org.web3s.model

final case class AbiDefinition(
                                constant: Boolean,
                                inputs: List[AbiDefinition.NamedType],
                                name: String,
                                outputs: List[AbiDefinition.NamedType],
                                `type`: String,
                                payable: Boolean,
                                stateMutability: StateMutability
                              )

object AbiDefinition:
  final case class NamedType(
                              name: String,
                              `type`: String,
                              components: List[NamedType],
                              internalType: String,
                              indexed: Boolean
                            ):
    def isDynamic: Boolean = `type` match
      case "string" | "bytes" | "[]" => true
      case _ => components.exists(_.isDynamic)

    def nestedness: Int = components.map(_.nestedness).sum

    def structIdentifier: Int =
      val typeStr = if internalType.isEmpty then `type` else internalType
      val subStructIdentifier = components.view.map(_.structIdentifier).map(String.valueOf).mkString
      (typeStr ++ subStructIdentifier).hashCode