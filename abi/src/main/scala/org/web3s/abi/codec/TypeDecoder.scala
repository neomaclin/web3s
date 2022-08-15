package org.web3s.abi.codec

import izumi.reflect.Tag
import org.web3s.abi.codec.decoders.DecoderMacro
import org.web3s.abi.datatypes.{EthArray, EthNumericType, EthType, EthUInt, StaticArray}
import org.web3s.abi.datatypes.EthType.MAX_BYTE_LENGTH
import decoders.given

object TypeDecoder:
  final val MAX_BYTE_LENGTH_FOR_HEX_STRING = MAX_BYTE_LENGTH << 1
  inline def decode[T <: EthType[_]](data: String, offset: Int = 0 )(using inline decoder:Decodable[T]): T = decoder.decode(data, offset)
  inline def decode[A[N <: EthType[_]] <: StaticArray[N], T <: EthType[_]](data: String, offset: Int, length: Int)(using inline decoder:Decodable[T], decodableSeq: DecodableSeq[A,T]): Seq[T] = decodableSeq.decode(data, offset, length)

