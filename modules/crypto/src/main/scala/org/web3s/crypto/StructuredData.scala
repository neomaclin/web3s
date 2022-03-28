package org.web3s.crypto

import org.web3s.abi.datatypes.{Address, Uint}

import scala.util.matching.Regex

object StructuredData:
  
  final case class Entry(name: String, `type`: String)

  final case class EIP712Domain(name: String,
                                version: String,
                                chainId: Uint,
                                verifyingContract: Address,
                                salt: String)

  final case class EIP712Message(types: Map[String, List[StructuredData.Entry]],
                                 primaryType: String,
                                 message: AnyRef,
                                 domain: StructuredData.EIP712Domain)

  object Encoder:
    private val arrayTypeRegex = """^([a-zA-Z_$][a-zA-Z_$0-9]*)((\\[([1-9]\\d*)?\\])+)$""".r
    private val bytesTypeRegex = """^bytes[0-9][0-9]?$""".r
    private val arrayDimensionRegex = """\\[([1-9]\\d*)?\\]""".r
    private val typeRegex = """^[a-zA-Z_$][a-zA-Z_$0-9]*(\\[([1-9]\\d*)*\\])*$""".r
    private val identifierRegex = """^[a-zA-Z_$][a-zA-Z_$0-9]*$""".r
  end Encoder
   
end StructuredData


