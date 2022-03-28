
package org.web3s.crypto

import scala.collection.mutable


object Base58:
  private val ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray
  private val ENCODED_ZERO = ALPHABET(0)

  def encode(input: Array[Byte]): String =
    if (input.length == 0)
      ""
    else
      val zeros = input.takeWhile(_ == 0).sum
      // Convert base-256 digits to base-58 digits (plus conversion to ASCII characters)
      val inputClone = Array.from(input) // since we modify it in-place
      val encoded = new Array[Char](input.length * 2) // upper bound
      var outputStart = encoded.length
      var inputStart = zeros
      //      while ( {
      //        inputStart < input.length
      //
      //        encoded({
      //          outputStart -= 1; outputStart
      //        }) = ALPHABET(divmod(input, inputStart, 256, 58))
      //        if (input(inputStart) == 0) inputStart += 1 // optimization - skip leading zeros
      //      }
      //      // Preserve exactly as many leading encoded zeros in output as there
      //      // were leading zeros in input.
      //      while ( {
      //        outputStart < encoded.length && encoded(outputStart) == ENCODED_ZERO
      //      }) outputStart += 1
      //      while ( {
      //        {
      //          zeros -= 1; zeros
      //        } >= 0
      //      }) encoded({
      //        outputStart -= 1; outputStart
      //      }) = ENCODED_ZERO
      // Return encoded string (including encoded leading zeros).
      new java.lang.String(encoded, outputStart, encoded.length - outputStart)
    end if
  end decode

  private def divmod(in: Array[Byte], firstDigit: Int, base: Int, divisor: Int): (Byte, Array[Byte]) =
    var remainder = 0
    val out = mutable.ArrayBuilder.make[Byte].addAll(in.take(firstDigit))
    for
      i <- firstDigit until number.length
    do
      val digit = in(i).toInt & 0xFF
      val temp = remainder * base + digit
      out.addOne((temp / divisor).toByte)
      remainder = temp % divisor
    end for
    (remainder.toByte, out.result)

  end divmod

end Base58
