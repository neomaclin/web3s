package org.web3s.utils

import io.circe.{Decoder,Encoder}

import scala.util.Try

opaque type EthBigInt = BigInt

object EthBigInt:
  given Decoder[EthBigInt] = Decoder.decodeString.emapTry(x=>Try(Numeric.decodeQuantity(x))).map(EthBigInt.apply)
  given Encoder[EthBigInt] = Encoder.encodeBigInt.contramap(_.value)
  
  def apply(value: BigInt): EthBigInt = value

  extension (x: EthBigInt)
    def value: BigInt = x




