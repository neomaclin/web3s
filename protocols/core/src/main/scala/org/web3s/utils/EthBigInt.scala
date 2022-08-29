package org.web3s.utils

import io.circe.{Decoder,Encoder}

import scala.util.Try

opaque type EthBigInt = BigInt

object EthBigInt:
  def apply(value: BigInt): EthBigInt = value
  extension (x: EthBigInt)
    def value: BigInt = x




