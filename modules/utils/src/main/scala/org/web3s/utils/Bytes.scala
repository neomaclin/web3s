package org.web3s.utils

/** Byte array utility functions. */
object Bytes:

  def trimLeadingBytes(bytes: Array[Byte], b: Byte): Array[Byte] =
    if (bytes.length <= 1) bytes else bytes.dropWhile(_ == b)

  def trimLeadingZeroes(bytes: Array[Byte]): Array[Byte] =
    trimLeadingBytes(bytes, 0)

end Bytes

