package org.web3s.protocol.core.methods.response


import org.web3s.model.AbiDefinition
import org.web3s.protocol.core.Response

opaque type EthCompileSolidity = Response[Map[String, EthCompileSolidity.Code]]

object EthCompileSolidity:

  def apply(response: Response[Map[String, Code]]): EthCompileSolidity = response

  final case class Code(
                         code: String,
                         info: SolidityInfo
                       )
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

