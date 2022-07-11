package org.web3s.crypto

import scala.collection.mutable

object Base58:

  private val ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray
  private val ENCODED_ZERO = ALPHABET(0)

  def encode(input: Array[Byte]): String =
    if (input.length == 0)
      ""
    else
      var zeros = input.takeWhile(_ == 0).sum

      // Convert base-256 digits to base-58 digits (plus conversion to ASCII characters)
      var inputClone = Array.from(input) // since we modify it in-place
      val encoded = new Array[Char](input.length * 2) // upper bound
      var outputStart = encoded.length
      var inputStart = zeros

      while
        inputStart < input.length
      do
        outputStart = outputStart - 1
        val (index, out) = divmod(inputClone, inputStart,256,58)
        encoded(outputStart) = ALPHABET(index)
        if (out(inputStart) == 0) { inputStart = (inputStart + 1).toByte }
        inputClone = out
      end while

      while
        outputStart < encoded.length && encoded(outputStart) == ENCODED_ZERO
      do
        outputStart = outputStart + 1
      end while

      while
        zeros >= 0
      do
        zeros = (zeros - 1).toByte
        outputStart = outputStart - 1
        encoded(outputStart) = ENCODED_ZERO
      end while
      outputStart = outputStart + 1
      new java.lang.String(encoded, outputStart, encoded.length - outputStart)
    end if
  end encode

  private def divmod(in: Array[Byte], firstDigit: Int, base: Int, divisor: Int): (Byte, Array[Byte]) =
    var remainder = 0
    val out = mutable.ArrayBuilder.make[Byte].addAll(in.take(firstDigit))
    for
      i <- firstDigit until in.length
    do
      val digit = in(i).toInt & 0xFF
      val temp = remainder * base + digit
      out.addOne((temp / divisor).toByte)
      remainder = temp % divisor
    end for
    (remainder.toByte, out.result)

  end divmod

end Base58
