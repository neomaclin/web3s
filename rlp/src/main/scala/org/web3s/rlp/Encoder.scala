package org.web3s.rlp

import org.web3s.rlp.Decoder.{OFFSET_SHORT_LIST, OFFSET_SHORT_STRING}

object Encoder:

  def encode(value: RlpType): Array[Byte] = value match
    case s: RlpString => encodeString(s)
    case l: RlpList => encodeList(l)

  private def encode(bytesValue: Array[Byte], offset: Int): Array[Byte] =

    if (bytesValue.length == 1 && offset == OFFSET_SHORT_STRING &&
      bytesValue(0) >= 0x00.toByte && bytesValue(0) <= 0x7f.toByte) bytesValue
    else if (bytesValue.length <= 55)
      val result = new Array[Byte](bytesValue.length + 1)
      result(0) = (offset + bytesValue.length).toByte
      Array.copy(bytesValue, 0, result, 1, bytesValue.length)
      result
    else
      val encodedStringLength = toMinimalByteArray(bytesValue.length)
      val result = new Array[Byte](bytesValue.length + encodedStringLength.length + 1)
      result(0) = ((offset + 0x37) + encodedStringLength.length).toByte
      Array.copy(encodedStringLength, 0, result, 1, encodedStringLength.length)
      Array.copy(bytesValue, 0, result, encodedStringLength.length + 1, bytesValue.length)
      result
    end if

  end encode


  private def encodeString(value: RlpString) = encode(value.value, OFFSET_SHORT_STRING)

  private def toMinimalByteArray(value: Int): Array[Byte] = toByteArray(value).dropWhile(_ == 0.toByte)

  private def toByteArray(value: Int) =
    Array[Byte](
      ((value >> 24) & 0xff).toByte,
      ((value >> 16) & 0xff).toByte,
      ((value >> 8) & 0xff).toByte,
      (value & 0xff).toByte
    )

  private def encodeList(value: RlpList) =
    val values = value.values
    if (values.isEmpty) encode(Array.emptyByteArray, OFFSET_SHORT_LIST)
    else encode(values.foldLeft(new Array[Byte](0))(_ ++ encode(_)), OFFSET_SHORT_LIST)
  end encodeList

end Encoder

