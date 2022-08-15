package org.web3s.abi.codec.decoders

import org.web3s.abi.datatypes.{StaticArray, *}

import scala.quoted.*

object DecoderMacro {
  inline def initiateUInt[T <: EthUInt](value: BigInt): T = ${initiateUIntImpl[T]('value)}

  private def initiateUIntImpl[T <: EthUInt : Type](value: Expr[BigInt])(using quotes: Quotes): Expr[T] =
    import quotes.reflect.*
    val sym = TypeRepr.of[T].typeSymbol

    Apply(Select(New(TypeTree.of[T]), sym.primaryConstructor), List(value.asTerm)).asExprOf[T]


  inline def initiateInt[T <: EthInt](value: BigInt): T = ${initiateIntImpl[T]('value)}


  private def initiateIntImpl[T <: EthInt : Type](value: Expr[BigInt])(using quotes: Quotes): Expr[T] =
    import quotes.reflect.*
    val sym = TypeRepr.of[T].typeSymbol
    Apply(Select(New(TypeTree.of[T]), sym.primaryConstructor), List(value.asTerm)).asExprOf[T]

  inline def initiateBytes[T <: Bytes](value: Array[Byte]): T = ${initiateBytesImpl[T]('value)}

  private def initiateBytesImpl[T <: Bytes : Type](value: Expr[Array[Byte]])(using quotes: Quotes): Expr[T] =
    import quotes.reflect.*
    val sym = TypeRepr.of[T].classSymbol.get
    Apply(Select(New(TypeTree.of[T]), sym.primaryConstructor), List(value.asTerm)).asExprOf[T]

//  inline def initiateStaticArrays[T <: EthType[_], A[N <: EthType[_]] <: StaticArray[N] ](value: Seq[T], tag: Tag[T]): A[T]  = ${initiateStaticArraysImpl[T,A]('value,'tag)}
//  private def initiateStaticArraysImpl[T <: EthType[_]: Type, A[N <: EthType[_]] <: StaticArray[N] : Type ](value: Expr[Seq[T]], tag: Expr[Tag[T]])(using quotes: Quotes): Expr[A[T]] =
//    import quotes.reflect.*
//    val sym = TypeRepr.of[A].typeSymbol
//    Apply(Select(New(TypeTree.of[A]), sym.primaryConstructor), List(value.asTerm,tag.asTerm)).asExprOf[A[T]]


}
