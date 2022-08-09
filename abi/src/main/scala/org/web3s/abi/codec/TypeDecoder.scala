package org.web3s.abi.codec

import izumi.reflect.Tag
import org.web3s.abi.codec.decoders.DecoderMacro
import org.web3s.abi.datatypes.{EthNumericType, EthType, EthUInt}
import org.web3s.abi.datatypes.EthType.MAX_BYTE_LENGTH

object TypeDecoder:
  final val MAX_BYTE_LENGTH_FOR_HEX_STRING = MAX_BYTE_LENGTH << 1
  inline def decode[T <: EthType[_]](data: String, offset: Int = 0)(using inline decoder:Decodable[T]): T = decoder.decode(data, offset)

  //inline def instantiateType[T <: EthType[_]: Decodable: Tag](value: Decodable#PT): T = summon[Decodable[T]].decode(data)
