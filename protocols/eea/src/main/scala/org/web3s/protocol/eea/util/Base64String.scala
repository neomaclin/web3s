package org.web3s.protocol.eea.util

import org.web3s.rlp.RlpString

import java.util.Base64
import eu.timepit.refined._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.numeric._
import eu.timepit.refined.collection._

opaque type Base64String = Array[Byte] Refined Size[32]

object Base64String:
  private val base64StringRegex = "(?:[A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=)".r
  val DECODER: Base64.Decoder = Base64.getDecoder
  val ENCODER: Base64.Encoder = Base64.getEncoder

  def apply(value: String): Base64String = value match
    case base64StringRegex(v) => DECODER.decode(v).take(32)
    case _ => Array.fill[Byte](32)(0.toByte)

  def apply(value: Array[Byte]): Base64String = value

extension (x: Base64String)
  def toString: String = Base64String.ENCODER.encodeToString(x.value)
  def raw: Array[Byte] = x.value
  def toRlp: RlpString = RlpString(x.value)



