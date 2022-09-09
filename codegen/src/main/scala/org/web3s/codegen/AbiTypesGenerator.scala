package org.web3s.codegen

import cats.effect.{Concurrent, ExitCode, IO, IOApp}
import fs2.io.file.{Files, Path}
import fs2.{Stream, hash, text}
import org.web3s.abi.datatypes.{EthArray, EthType}
import java.io.File

object AbiTypesGenerator extends Generator with IOApp :

  val packageName = "org.web3s.abi.datatypes.generated"
  val destDir: String =
    (System.getProperty("user.dir") + "/abi/src/main/scala/" + packageName.replace('.', '/')).replace('/', File.separatorChar) + File.separatorChar
  
  override def run(args: List[String]): IO[ExitCode] = 
    for 
      _ <- write[IO](generateIntTypes, destDir)
      _ <- write[IO](generateUIntTypes, destDir)
      _ <- write[IO](generateBytesTypes, destDir)
      _ <- write[IO](generateStaticArrayTypes, destDir)
    yield 
      ExitCode.Success

  def generateIntTypes[F[_] : Files : Concurrent]: Stream[F, (String, String)] =
    def intTemplate(size: Int): (String, String) =
      (
        s"""package org.web3s.abi.datatypes.generated
           |
           |import org.web3s.abi.datatypes.EthInt
           |
           |object Int$size:
           |  val DEFAULT = Int$size(BigInt(0))
           |
           |final case class Int$size(override val value: BigInt) extends EthInt($size, value):
           |  def this(value: Long) = this(BigInt(value))
           |""".stripMargin, s"""Int$size.scala""")
    end intTemplate

    Stream.iterable(8.to(EthType.MAX_BIT_LENGTH, 8)).map(intTemplate)
  
  def generateUIntTypes[F[_] : Files : Concurrent]: Stream[F, (String, String)] =
    def uintTemplate(size: Int): (String, String) =
      (
        s"""package org.web3s.abi.datatypes.generated
           |
           |import org.web3s.abi.datatypes.EthUInt
           |
           |object UInt$size:
           |  val DEFAULT = UInt$size(BigInt(0))
           |
           |final case class UInt$size(override val value: BigInt) extends EthUInt($size, value):
           |  def this(value: Long) = this(BigInt(value))
           |""".stripMargin, s"""UInt$size.scala""")
    end uintTemplate

    Stream.iterable(8.to(EthType.MAX_BIT_LENGTH, 8)).map(uintTemplate)
  
  def generateBytesTypes[F[_] : Files : Concurrent]: Stream[F, (String, String)] =
    def byteTemplate(size: Int): (String, String) =
      (
        s"""package org.web3s.abi.datatypes.generated
           |
           |import org.web3s.abi.datatypes.Bytes
           |
           |object Bytes$size:
           |  val DEFAULT = Bytes$size(new Array[Byte]($size))
           |
           |final case class Bytes$size(override val value: Array[Byte]) extends Bytes($size, value)
           |""".stripMargin, s"""Bytes$size.scala""")
    end byteTemplate

    Stream.iterable(1 to 32).map(byteTemplate)
  

  def generateStaticArrayTypes[F[_] : Files : Concurrent]: Stream[F, (String, String)] =
    def staticArrayTemplate(size: Int): (String, String) =
      (
        s"""package org.web3s.abi.datatypes.generated
           |
           |import org.web3s.abi.datatypes.{StaticArray, EthType}
           |import izumi.reflect.Tag
           |import scala.reflect.ClassTag
           |
           |final class StaticArray$size[T <: EthType[_] : Tag: ClassTag](values: T*) extends StaticArray[T]($size, values)
           |
           |""".stripMargin, s"""StaticArray$size.scala""")
    end staticArrayTemplate

    Stream.iterable(0 to EthArray.MAX_SIZE_OF_STATIC_ARRAY).map(staticArrayTemplate)
  