package org.web3s.abi.codec.decoders

import org.web3s.abi.datatypes.*
import scala.quoted.*

object DecoderMacro {
  inline def initiateUInt[T <: EthUInt](value: BigInt): T = ${initiateUIntImpl[T]('value)}

  private def initiateUIntImpl[T <: EthUInt : Type](value: Expr[BigInt])(using quotes: Quotes): Expr[T] =
    import quotes.reflect.*
    val sym = TypeRepr.of[T].classSymbol.get
    Apply(Select(New(TypeTree.of[T]), sym.primaryConstructor), List(value.asTerm)).asExprOf[T]


  inline def initiateInt[T <: EthInt](value: BigInt): T = ${initiateIntImpl[T]('value)}
  //  inline def showType[T <: EthUInt]: String = ${ showTypeImpl[T]}
  //
  //  private  def showTypeImpl[T <: EthUInt : Type](using quotes: Quotes): Expr[String] =
  //    import quotes.reflect.*
  //    Expr(TypeRepr.of[T].classSymbol.get.primaryConstructor.name)


  private def initiateIntImpl[T <: EthInt : Type](value: Expr[BigInt])(using quotes: Quotes): Expr[T] =
    import quotes.reflect.*
    val sym = TypeRepr.of[T].classSymbol.get
    Apply(Select(New(TypeTree.of[T]), sym.primaryConstructor), List(value.asTerm)).asExprOf[T]

  inline def initiateBytes[T <: Bytes](value: Array[Byte]): T = ${initiateBytesImpl[T]('value)}

  private def initiateBytesImpl[T <: Bytes : Type](value: Expr[Array[Byte]])(using quotes: Quotes): Expr[T] =
    import quotes.reflect.*
    val sym = TypeRepr.of[T].classSymbol.get
    Apply(Select(New(TypeTree.of[T]), sym.primaryConstructor), List(value.asTerm)).asExprOf[T]


}
