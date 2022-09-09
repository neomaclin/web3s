package org.web3s.abi.codec

import izumi.reflect.Tag
import org.web3s.abi.datatypes.{EthArray, EthType}

trait Decodable[T <: EthType[_]]:
  def decode(data: String, offset: Int): T

trait DecodableArray[T <: EthType[_], A[T<: EthType[_]] <: EthArray[T]]:
  def decode(data: String, offset: Int, length: Int): A[T]
