
package org.web3s.utils

import org.web3s.exceptions.{MessageDecodingException, MessageEncodingException}

import scala.util.*

object Numeric:

  private final val HEX_PREFIX = "0x"
  private final val HEX_CHAR_MAP = """0123456789abcdef""".toCharArray

  @throws[MessageEncodingException]
  def encodeQuantity(value: BigInt): String =
    if (value.signum != -1) HEX_PREFIX + value.toString(16)
    else throw MessageEncodingException("Negative values are not supported")

  @throws[MessageDecodingException]
  def decodeQuantity(value: String): BigInt =
    if (isLongValue(value)) BigInt(value)
    else if (!isValidHexQuantity(value))
      throw new MessageDecodingException("Value must be in format 0x[1-9]+[0-9]* or 0x0")
    else try BigInt(value.substring(2), 16) catch
      case e: NumberFormatException => throw new MessageDecodingException("Negative ", e)
    end if
  end decodeQuantity

  private def isLongValue(value: String) =
    try
      value.toLong
      true
    catch
       case e: NumberFormatException => false
  end isLongValue


  private def isValidHexQuantity(value: String) = (value.length >= 3) && value.startsWith(HEX_PREFIX)

  def cleanHexPrefix(input: String): String = if (containsHexPrefix(input)) input.drop(2) else input

  def prependHexPrefix(input: String): String = if (!containsHexPrefix(input)) HEX_PREFIX + input else input

  def containsHexPrefix(input: String): Boolean = input.nonEmpty && input.startsWith("0x")

  def toBigInt(value: Array[Byte], offset: Int, length: Int): BigInt = toBigInt(value.slice(offset, offset + length))

  def toBigInt(value: Array[Byte]): BigInt = BigInt(1, value)

  def toBigInt(hexValue: String): BigInt = toBigIntNoPrefix(cleanHexPrefix(hexValue))

  def toBigIntNoPrefix(hexValue: String): BigInt = BigInt(hexValue, 16)

  def toHexStringWithPrefix(value: BigInt): String = HEX_PREFIX + value.toString(16)

  def toHexStringNoPrefix(value: BigInt): String = value.toString(16)

  def toHexStringNoPrefix(input: Array[Byte]): String = toHexString(input, 0, input.length, false)

  def toHexStringWithPrefixZeroPadded(value: BigInt, size: Int): String = toHexStringZeroPadded(value, size, true)

  def toHexStringWithPrefixSafe(value: BigInt): String =
    var result = toHexStringNoPrefix(value)
    if (result.length < 2) result = "0" + result
    HEX_PREFIX + result
  end toHexStringWithPrefixSafe
  
  def toHexStringNoPrefixZeroPadded(value: BigInt, size: Int): String = toHexStringZeroPadded(value, size, false)

  @throws[UnsupportedOperationException]
  private def toHexStringZeroPadded(value: BigInt, size: Int, withPrefix: Boolean): String =
    var result = toHexStringNoPrefix(value)
    val length = result.length
    if (length > size) throw UnsupportedOperationException("Value " + result + "is larger then length " + size)
    else if (value.signum < 0) throw UnsupportedOperationException("Value cannot be negative")
    if (length < size) result = "0" * (size - length) + result
    if (withPrefix) HEX_PREFIX + result
    else result
  end toHexStringZeroPadded

  @throws[RuntimeException]
  def toBytesPadded(value: BigInt, length: Int): Array[Byte] =
    val result = new Array[Byte](length)
    val bytes = value.toByteArray
    var bytesLength = 0
    var srcOffset = 0

    if (bytes(0) == 0)
      bytesLength = bytes.length - 1
      srcOffset = 1
    else
      bytesLength = bytes.length
      srcOffset = 0
    end if

    if (bytesLength > length) throw new RuntimeException("Input is too large to put in byte array of size " + length)
    val destOffset = length - bytesLength
    System.arraycopy(bytes, srcOffset, result, destOffset, bytesLength)
    result
  end toBytesPadded

  def hexStringToByteArray(input: String): Array[Byte] =
    val cleanInput = cleanHexPrefix(input)
    val len = cleanInput.length
    
    if (len == 0) Array.emptyByteArray
    else
      val data = Array.newBuilder[Byte]
      var startIdx = 0
      
      if (len % 2 != 0)
        data.addOne(Character.digit(cleanInput.charAt(0), 16).toByte)
        startIdx = 1
      else
        startIdx = 0
      end if

      for 
        i <- startIdx until(len, 2) 
      do 
        data.addOne(((Character.digit(cleanInput.charAt(i), 16) << 4) + Character.digit(cleanInput.charAt(i + 1), 16)).toByte)
      
      data.result
    end if

  end hexStringToByteArray



  private def toHexCharArray(input: Array[Byte], offset: Int, length: Int, withPrefix: Boolean): Array[Char] =
    val output = Array.newBuilder[Char]
    
    for 
      i <- offset until length
    do
      val v = input(i) & 0xFF
      output.addOne(HEX_CHAR_MAP(v >>> 4))
      output.addOne(HEX_CHAR_MAP(v & 0x0F))
    
    output.result
  end toHexCharArray
  

  def toHexString(input: Array[Byte], offset: Int, length: Int, withPrefix: Boolean): String =
    val output = toHexCharArray(input, offset, length, withPrefix)
    (if (withPrefix) HEX_PREFIX else "") ++ output

  def toHexString(input: Array[Byte]): String = toHexString(input, 0, input.length, true)

  def asByte(m: Int, n: Int): Byte = ((m << 4) | n).toByte

  def isIntegerValue(value: BigDecimal): Boolean =
    value.signum == 0 || value.scale <= 0 || value.bigDecimal.stripTrailingZeros.scale <= 0

end Numeric
