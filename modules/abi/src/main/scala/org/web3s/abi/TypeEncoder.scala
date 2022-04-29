package org.web3s.abi

import izumi.reflect.Tag
import org.bouncycastle.util
import org.web3s.abi.datatypes.*
import org.web3s.abi.datatypes.SolidityType.MAX_BIT_LENGTH
import org.web3s.utils.Numeric

import scala.collection.mutable

object TypeEncoder:
  def encode[T <: SolidityType[_] : Tag: Encodable](value: T): String = summon[Encodable[T]].encode(value)

  def encodePacked[T <: SolidityType[_] : Tag: Encodable](value: T): String = summon[Encodable[T]].encodePacked(value)

