package org.web3s.protocol.core.methods.response


import org.web3s.protocol.core.Response

opaque type EthCompileSolidity = Response[Map[String, EthCompileSolidity.Code]]

object EthCompileSolidity:
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
  def apply(response: Response[String]): EthCompileSolidity = response

extension (x: EthCompileSolidity)
  def compiledSourceCode: BigInt = x.result
