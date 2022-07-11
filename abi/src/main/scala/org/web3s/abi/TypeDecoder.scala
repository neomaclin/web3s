package org.web3s.abi

import org.web3s.abi.datatypes.Bool
import org.web3s.abi.datatypes.SolidityType
import org.web3s.abi.datatypes.SolidityType.MAX_BYTE_LENGTH

object TypeDecoder:

  final val MAX_BYTE_LENGTH_FOR_HEX_STRING = MAX_BYTE_LENGTH << 1

  def decode[T <: SolidityType[_]](using Decodable[T])(data: String, offset: Int): T =
    summon[Decodable[T]].decode(data, offset)


end TypeDecoder

