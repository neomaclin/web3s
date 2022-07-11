package org.web3s

import org.web3s.utils.Numeric

package rlp:

  sealed trait RlpType

  final case class RlpList(values: Vector[RlpType] = Vector.empty) extends RlpType :
    def this(values: RlpType*) = this(values.toVector)
  end RlpList

  object RlpString:

    def from(value: Byte): RlpString = RlpString(Array[Byte](value))

    def from(value: BigInt): RlpString = // RLP encoding only supports positive integer values
      if (value.signum < 1) RlpString(Array.emptyByteArray)
      else RlpString(value.toByteArray.dropWhile(_ == 0))

    def from(value: Long): RlpString = from(BigInt(value))

    def from(value: String): RlpString = RlpString(value.getBytes)

  end RlpString


  final case class RlpString(value: Array[Byte]) extends RlpType :

    def asPositiveBigInt: BigInt = if (value.length == 0) BigInt(0) else BigInt(1, value)

    def asString: String = Numeric.toHexString(value)

    override def hashCode: Int = value.hashCode

    override def equals(o: Any): Boolean = o match
      case RlpString(otherValue) => value sameElements otherValue
      case _ => false
    
  end RlpString

end rlp

