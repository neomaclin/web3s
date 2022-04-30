package org.web3s.codegen

import cats.effect.{Concurrent, ExitCode, IO, IOApp}
import fs2.io.file.{Files, Path}
import fs2.{Stream, hash, text}
import org.web3s.abi.datatypes.{SolidityArray, SolidityType}
import java.io.File

object AbiTypesGenerator extends Generator with IOApp :

  val packageName = "org.web3s.abi.datatypes.generated"
  val destDir =
    (System.getProperty("user.dir") + "/modules/abi/src/main/scala/" + packageName.replace('.', '/')).replace('/', File.separatorChar) + File.separatorChar
  
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
           |import org.web3s.abi.datatypes.SolidityInt
           |
           |object Int$size:
           |  val DEFAULT = new Int$size(BigInt(0))
           |end Int$size
           |
           |final class Int$size(value: BigInt) extends SolidityInt($size, value):
           |  def this(value: Long) = this(BigInt(value))
           |end Int$size
           |""".stripMargin, s"""Int$size.scala""")
    end intTemplate

    Stream.iterable(8.to(SolidityType.MAX_BIT_LENGTH, 8)).map(intTemplate)
  end generateIntTypes

  def generateUIntTypes[F[_] : Files : Concurrent]: Stream[F, (String, String)] =
    def uintTemplate(size: Int): (String, String) =
      (
        s"""package org.web3s.abi.datatypes.generated
           |
           |import org.web3s.abi.datatypes.SolidityUInt
           |
           |object UInt$size:
           |  val DEFAULT = new UInt$size(BigInt(0))
           |end UInt$size
           |
           |final class UInt$size(value: BigInt) extends SolidityUInt($size, value):
           |  def this(value: Long) = this(BigInt(value))
           |end UInt$size
           |""".stripMargin, s"""UInt$size.scala""")
    end uintTemplate

    Stream.iterable(8.to(SolidityType.MAX_BIT_LENGTH, 8)).map(uintTemplate)
  end generateUIntTypes

  def generateBytesTypes[F[_] : Files : Concurrent]: Stream[F, (String, String)] =
    def byteTemplate(size: Int): (String, String) =
      (
        s"""package org.web3s.abi.datatypes.generated
           |
           |import org.web3s.abi.datatypes.Bytes
           |
           |object Bytes$size:
           |  val DEFAULT = new Bytes$size(new Array[Byte]($size))
           |end Bytes$size
           |
           |final class Bytes$size(override val value: Array[Byte]) extends Bytes($size, value)
           |""".stripMargin, s"""Bytes$size.scala""")
    end byteTemplate

    Stream.iterable(1 to 32).map(byteTemplate)
  end generateBytesTypes


  def generateStaticArrayTypes[F[_] : Files : Concurrent]: Stream[F, (String, String)] =
    def staticArrayTemplate(size: Int): (String, String) =
      (
        s"""package org.web3s.abi.datatypes.generated
           |
           |import org.web3s.abi.datatypes.{StaticArray, SolidityType}
           |import izumi.reflect.Tag
           |
           |final class StaticArray$size[T <: SolidityType[_] : Tag](values: List[T]) extends StaticArray($size, values) :
           |  def this(values: T*) = this(List(values*))
           |end StaticArray$size
           |
           |""".stripMargin, s"""StaticArray$size.scala""")
    end staticArrayTemplate

    Stream.iterable(0 to SolidityArray.MAX_SIZE_OF_STATIC_ARRAY).map(staticArrayTemplate)
  end generateStaticArrayTypes

end AbiTypesGenerator

