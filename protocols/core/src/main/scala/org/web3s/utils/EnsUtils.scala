package org.web3s.utils

import org.web3s.utils.Numeric

object EnsUtils:
  val EMPTY_ADDRESS = "0x0000000000000000000000000000000000000000"

  // Wildcard resolution
  val ENSIP_10_INTERFACE_ID: Array[Byte] = Numeric.hexStringToByteArray("0x9061b923")
  val EIP_3668_CCIP_INTERFACE_ID = "0x556f1830"

  def isAddressEmpty(address: String): Boolean = EMPTY_ADDRESS == address

  def isEIP3668(data: String): Boolean =
    if data.length < 10 then false else EnsUtils.EIP_3668_CCIP_INTERFACE_ID == data.substring(0, 10)

  def getParent(url: String): String =
    val ensUrl = url.trim
    if ensUrl == "." || !ensUrl.contains(".") then "" else ensUrl.substring(ensUrl.indexOf(".") + 1)


