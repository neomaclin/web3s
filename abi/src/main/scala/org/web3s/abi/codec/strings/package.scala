package org.web3s.abi.codec

import izumi.reflect.Tag
import org.web3s.abi.datatypes.*

package strings:

  given TypeAsStringConverter[EthUtf8String] with
    override def convert(t: EthUtf8String): String = "string"

  given TypeAsStringConverter[Address] with
    override def convert(t: Address): String = "address"

  given TypeAsStringConverter[Bool] with
    override def convert(t: Bool): String = "bool"

  given encodeInt[I <: EthInt : Tag]:TypeAsStringConverter[I] with
    override def convert(t: I): String = Tag[I].tag.toString

  given encodeUInt[U <: EthUInt : Tag]:TypeAsStringConverter[U] with
    override def convert(t: U): String = Tag[U].tag.toString

  given encodeBytes[B <: Bytes : Tag]: TypeAsStringConverter[B] with
    override def convert(t: B): String = t.`type`

  given TypeAsStringConverter[Fixed] with
    override def convert(t: Fixed): String = t.`type`

  given TypeAsStringConverter[UFixed] with
    override def convert(t: UFixed): String = t.`type`

  given encodeStaticArrayFor[T <: EthType[_] : Tag, A <: StaticArray[T]]: TypeAsStringConverter[A] with
    override def convert(t: A): String =
      if t.value.nonEmpty then s"${Tag[T].tag.toString}[{${t.value.size}}]"
      else t.componentTypeAsString + "[0]"
