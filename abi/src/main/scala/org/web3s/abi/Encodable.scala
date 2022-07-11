package org.web3s.abi

import izumi.reflect.Tag
import org.web3s.abi.datatypes.{SolidityArray, SolidityType}

trait Encodable[T <: SolidityType[_]]:

  def encode(value: T): String

  def encodePacked(value: T): String


trait EncodableSeq[S[_] <: SolidityArray[_]]:

  def encode[T <: SolidityType[_] : Tag](value: S[T]): String

  def encodePacked[T <: SolidityType[_] : Tag](value: S[T]): String
