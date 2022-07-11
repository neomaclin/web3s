package org.web3s.rlp

import scala.annotation.tailrec

object Decoder:

  val OFFSET_SHORT_STRING = 0x80
  val OFFSET_LONG_STRING = 0xb7
  val OFFSET_SHORT_LIST = 0xc0
  val OFFSET_LONG_LIST = 0xf7

  def decode(rlpEncoded: Array[Byte]): RlpList =
    traverse(rlpEncoded, 0, rlpEncoded.length, RlpList(Vector.empty[RlpType]))

  @throws[RuntimeException]
  private def traverse(data: Array[Byte], startPos: Int, endPos: Int, rlpList: RlpList): RlpList =
    if (endPos < 0 || endPos > data.length)
      RlpList(Vector.empty[RlpType])
    else
      var pos = startPos
      var result = rlpList
      while pos < endPos do
        data(pos) & 0xff match
          case prefix if prefix < OFFSET_SHORT_STRING =>
            result = RlpList(result.values.appended(RlpString.from(prefix.toByte)))
            pos = pos + 1
          case prefix if prefix == OFFSET_SHORT_STRING =>
            result = RlpList(result.values.appended(RlpString(Array.emptyByteArray)))
            pos = pos + 1
          case prefix if prefix > OFFSET_SHORT_STRING && prefix <= OFFSET_LONG_STRING =>
            val strLen = prefix - OFFSET_SHORT_STRING
            
            if (strLen > endPos - (pos + 1)) throw new RuntimeException("RLP length mismatch")
            
            val rlpData = new Array[Byte](strLen)
            Array.copy(data, pos + 1, rlpData, 0, strLen)
            result = RlpList(result.values.appended(RlpString(rlpData)))
            pos = pos + 1 + strLen
          case prefix if prefix > OFFSET_LONG_STRING && prefix < OFFSET_SHORT_LIST =>
            val lenOfStrLen = prefix - OFFSET_LONG_STRING
            val strLen = calcLength(lenOfStrLen, data, pos)

            if (strLen > endPos - (pos + lenOfStrLen + 1)) throw new RuntimeException("RLP length mismatch")

            val rlpData = new Array[Byte](strLen)
            Array.copy(data, pos + lenOfStrLen + 1, rlpData, 0, strLen)
            result =  RlpList(result.values.appended(RlpString(rlpData)))
            pos = pos  + lenOfStrLen + 1 + strLen
          case prefix if prefix >= OFFSET_SHORT_LIST && prefix <= OFFSET_LONG_LIST =>
            val listLen = prefix - OFFSET_SHORT_LIST
            val newLevelList = traverse(data, pos + 1, pos + listLen + 1, RlpList(Vector.empty[RlpType]))

            result =  RlpList(result.values.appended(newLevelList))
            pos = pos  + listLen + 1
          case prefix if prefix > OFFSET_LONG_LIST =>
            val lenOfListLen = prefix - OFFSET_LONG_LIST
            val listLen = calcLength(lenOfListLen, data, pos)
            val newLevelList = traverse(
              data,
              pos + lenOfListLen + 1,
              pos + lenOfListLen + listLen + 1,
              RlpList(Vector.empty[RlpType]))
            result =  RlpList(result.values.appended(newLevelList))
            pos = pos  + lenOfListLen + listLen + 1
        end match
      end while
      result
    end if
  end traverse
  
  @throws[RuntimeException]
  private def calcLength(lengthOfLength: Int, data: Array[Byte], pos: Int): Int = 
    var pow: Byte = (lengthOfLength - 1).toByte
    var length: Long = 0L
    
    for 
     i <- 1 to lengthOfLength
    do 
      length = length + (data(pos + i) & 0xff).toLong << (8 * pow)
      pow = (pow - 1).toByte
    
    if (length < 0 || length > Integer.MAX_VALUE) throw new RuntimeException("RLP too many bytes to decode")
    
    length.toInt
  end calcLength
  
end Decoder
