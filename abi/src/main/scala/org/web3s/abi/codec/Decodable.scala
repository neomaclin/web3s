package org.web3s.abi.codec

import izumi.reflect.Tag
import org.web3s.abi.datatypes.{EthArray, EthType}
trait Decodable[T <: EthType[_]]:
  def decode(data: String, offset: Int): T

trait DecodableSeq[A[I<: EthType[_]] <: EthArray[I],T <: EthType[_]]:
  def decode(data: String, offset: Int, length: Int): Seq[T]