package org.web3s.abit.datatypes

import org.web3s.abi.Encodable
import org.web3s.abi.datatypes.generated.StaticArray2
import org.web3s.abi.datatypes.{DynamicArray, DynamicStruct, StaticArray, Utf8String}

final case class Foo(id: String, name: String) extends DynamicStruct[Utf8String](new Utf8String(id),new Utf8String(name))

object Foo:
  given Encodable[Foo] = new Encodable[Foo]:
    override def encode(value: Foo): String = StaticArray.encode(value)
    override def encodePacked(value: Foo): String = StaticArray.encode(value)
