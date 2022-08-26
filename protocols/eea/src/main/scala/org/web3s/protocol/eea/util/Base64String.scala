package org.web3s.protocol.eea.util

import cats.Eq
import org.web3s.rlp.RlpString

import java.util.Base64

type Base64String = Array[Byte]

object Base64String:
  extension (x: Base64String)
    def asString: String = Base64String.ENCODER.encodeToString(x)
    def raw: Array[Byte] = x
    def toRlp: RlpString = RlpString(x)

  private val regex = """(?:[A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=)""".r
  val DECODER: Base64.Decoder = Base64.getDecoder
  val ENCODER: Base64.Encoder = Base64.getEncoder
  private def isValid(x: String): Boolean = regex.matches(x)
  def apply(value: String): Base64String =
    if !isValid(value) || value.length != 44 then
      throw new IllegalArgumentException(value + " is not a 32 byte base 64 value")
    else DECODER.decode(value)

  def apply(value: Array[Byte]): Base64String = apply(ENCODER.encodeToString(value))
  given Eq[Base64String] = Eq.instance( _ sameElements _)

  given io.circe.Encoder[Base64String] = io.circe.Encoder.encodeString.contramap(_.asString)