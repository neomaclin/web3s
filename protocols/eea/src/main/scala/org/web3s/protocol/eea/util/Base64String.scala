package org.web3s.protocol.eea.util

import org.web3s.protocol.eea.util.Base64String.wrap
import org.web3s.rlp.RlpString

import java.util.Base64

opaque type Base64String = Array[Byte]

object Base64String:

  private val regex = """(?:[A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=)""".r
  val DECODER: Base64.Decoder = Base64.getDecoder
  val ENCODER: Base64.Encoder = Base64.getEncoder
  private def isValid(x: String): Boolean = regex.matches(x)
  def wrap(value: String): Base64String =
    if !isValid(value) || value.length != 44 then
      throw new IllegalArgumentException(value + " is not a 32 byte base 64 value")
    else DECODER.decode(value)

  def wrapList(value: List[String]): List[Base64String] = value.map(wrap)
  def wrapList(value: String, values: String*): List[Base64String] = (value :: values.toList).map(wrap)
  def apply(value: Array[Byte]): Base64String = wrap(ENCODER.encodeToString(value))

extension (x: Base64String)
  def asString: String = Base64String.ENCODER.encodeToString(x)
  def raw: Array[Byte] = x
  def equals(other: Base64String): Boolean = x.asString == other.asString
  def toRlp: RlpString = RlpString(x)
