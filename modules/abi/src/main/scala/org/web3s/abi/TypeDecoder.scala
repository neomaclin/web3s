package org.web3s.abi

import org.web3s.abi.datatypes.Bool
import org.web3s.abi.datatypes.SolidityType
import org.web3s.abi.datatypes.SolidityType.MAX_BYTE_LENGTH

trait TypeDecoder[T<: SolidityType[_]]:
  def decode(input: String, offset: Int): T

trait PrimaryTypeDecoder[T<:AnyVal]:
  def decode(input: String, offset: Int): T

object TypeDecoder:
  
  final val MAX_BYTE_LENGTH_FOR_HEX_STRING = MAX_BYTE_LENGTH << 1

//  def decode[T <: SolidityType[_]](using decoder: TypeDecoder[T])(data: String, offset: Int): T =
//    decoder.decode(data,offset)


end TypeDecoder

