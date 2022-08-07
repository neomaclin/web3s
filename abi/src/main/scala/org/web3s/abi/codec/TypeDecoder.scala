package org.web3s.abi.codec

import izumi.reflect.Tag
import org.web3s.abi.datatypes.EthType
import org.web3s.abi.datatypes.EthType.MAX_BYTE_LENGTH

object TypeDecoder:
  final val MAX_BYTE_LENGTH_FOR_HEX_STRING = MAX_BYTE_LENGTH << 1
  def decode[T <: EthType[_]: Decodable: Tag](data: String, offset: Int): T = summon[Decodable[T]].decode(data, offset)


