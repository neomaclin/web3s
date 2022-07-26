package org.web3s.protocol.eea.util

import cats.Eq
import org.scalatest.funsuite.AnyFunSuite
import cats.syntax.eq.catsSyntaxEq
import cats.instances.*

class Base64StringTest extends AnyFunSuite :

  import Base64String.*

  given Eq[Array[Byte]] = Eq.instance(_ sameElements _)

  private val BASE64_1 = "A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo="
  private val BASE64_2 = "Ko2bVqD+nNlNYL5EE7y3IdOnviftjiizpjRt+HTuFBs="
  private val BASE64_3 = "DyAOiF/ynpc+JXa2YAGB0bCitSlOMNm+ShmB/7M6C4w="
  private val BASE64_BYTES_1 = Array[Byte](
    3, 86, -107, -76, -52, 75, 9, 65, -26, 5, 81, -41, -95, -100, -13, 6, 3, -37, 91,
    -4, 35, -27, -84, 67, -91, 111, 87, -14, 95, 117, 72, 106
  )
  private val BASE64_BYTES_2 = Array[Byte](
    42, -115, -101, 86, -96, -2, -100, -39, 77, 96, -66, 68, 19, -68, -73, 33, -45, -89,
    -66, 39, -19, -114, 40, -77, -90, 52, 109, -8, 116, -18, 20, 27
  )
  private val BASE64_BYTES_3 = Array[Byte](
    15, 32, 14, -120, 95, -14, -98, -105, 62, 37, 118, -74, 96, 1, -127, -47, -80, -94,
    -75, 41, 78, 48, -39, -66, 74, 25, -127, -1, -77, 58, 11, -116
  )
  private val BASE64_LIST = List(BASE64_1, BASE64_1)
  private val BASE64_WRAPPED = Base64String("A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo=")
  private val BASE64_WRAPPED_LIST: List[Base64String] = List(BASE64_WRAPPED, BASE64_WRAPPED)

  test("WrapList") {
    assert(BASE64_WRAPPED_LIST eqv BASE64_LIST.map(Base64String.apply))
  }

  test("UnwrapList") {
    assert(BASE64_WRAPPED_LIST.map(_.asString) eqv BASE64_LIST)
  }

  test("ValidBase64String") {
    val base64String1 = Base64String(BASE64_1)
    val base64String2 = Base64String(BASE64_2)
    val base64String3 = Base64String(BASE64_3)

    assert(BASE64_1 eqv base64String1.asString)
    assert(BASE64_2 eqv base64String2.asString)
    assert(BASE64_3 eqv base64String3.asString)

    assert(BASE64_BYTES_1 eqv base64String1.raw)
    assert(BASE64_BYTES_2 eqv base64String2.raw)
    assert(BASE64_BYTES_3 eqv base64String3.raw)
  }

  test("ValidBase64ByteArray") {
    val base64String1 = Base64String(BASE64_BYTES_1)
    val base64String2 = Base64String(BASE64_BYTES_2)
    val base64String3 = Base64String(BASE64_BYTES_3)

    assert(BASE64_1 eqv base64String1.asString)
    assert(BASE64_2 eqv base64String2.asString)
    assert(BASE64_3 eqv base64String3.asString)

    assert(BASE64_BYTES_1 eqv base64String1.raw)
    assert(BASE64_BYTES_2 eqv base64String2.raw)
    assert(BASE64_BYTES_3 eqv base64String3.raw)
  }

  test("EmptyStringThrows") {
    assertThrows[RuntimeException] {
      Base64String("")
    }
  }
  test("TooShortStringThrows") {
    assertThrows[RuntimeException] {
      Base64String(BASE64_1.substring(0, 43))
    }
  }

  test("TooLongStringThrows") {
    assertThrows[RuntimeException] {
      Base64String(BASE64_1 + "m")
    }
  }

  test("NonValidBase64StringThrows") {
    assertThrows[RuntimeException] {
      Base64String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqr")
    }
  }

  test("EmptyByteArrayThrows") {
    assertThrows[RuntimeException] {
      Base64String(Array.emptyByteArray)
    }
  }

  test("TooShortByteArrayThrows") {
    assertThrows[RuntimeException] {
      Base64String(Array.copyOf(BASE64_BYTES_1, 31))
    }
  }

  test("TooLongByteArrayThrows") {
    assertThrows[RuntimeException] {
      Base64String(Array.copyOf(BASE64_BYTES_1, 33))
    }
  }