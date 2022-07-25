package org.web3s.utils

import io.circe.Decoder

import scala.util.Try

opaque type EthBigInt = BigInt

object EthBigInt:
  given Decoder[EthBigInt] = Decoder.decodeString.emapTry(x=>Try(Numeric.decodeQuantity(x))).map(EthBigInt.apply)
  
  def apply(value: BigInt): EthBigInt = value

  extension (x: EthBigInt)
    def value: BigInt = x




