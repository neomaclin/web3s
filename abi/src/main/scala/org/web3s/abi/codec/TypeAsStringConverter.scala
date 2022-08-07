package org.web3s.abi.codec

trait TypeAsStringConverter[T]:
  def convert(t: T): String

object TypeAsStringConverter:
  def convert[T](t: T)(using conv: TypeAsStringConverter[T]): String = conv.convert(t)

given TypeAsStringConverter[EmptyTuple] with
  def convert(empty: EmptyTuple) = ""
  
// Inductive case
given [H: TypeAsStringConverter, T <: Tuple: TypeAsStringConverter]: TypeAsStringConverter[H *: T] with
  def convert(tuple: H *: T) = TypeAsStringConverter.convert(tuple.head) +","+ TypeAsStringConverter.convert(tuple.tail)

