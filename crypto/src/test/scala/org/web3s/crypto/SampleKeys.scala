package org.web3s.crypto

import org.web3s.utils.Numeric

object SampleKeys:
  
  val PRIVATE_KEY_STRING = "a392604efc2fad9c0b3da43b5f698a2e3f270f170d859912be0d54742275c5f6"
  val PUBLIC_KEY_STRING: String = "0x506bc1dc099358e5137292f4efdd57e400f29ba5132aa5d12b18dac1c1f6aab" + "a645c0b7b58158babbfa6c6cd5a48aa7340a8749176b120e8516216787a13dc76"
  val ADDRESS = "0xef678007d18427e6022059dbc264f27507cd1ffc"
  val ADDRESS_NO_PREFIX: String = Numeric.cleanHexPrefix(ADDRESS)
  val PASSWORD = "Insecure Pa55w0rd"
  val MNEMONIC: String = "scatter major grant return flee easy female jungle" + " vivid movie bicycle absent weather inspire carry"
  val PRIVATE_KEY: BigInt = Numeric.toBigInt(PRIVATE_KEY_STRING)
  val PUBLIC_KEY: BigInt = Numeric.toBigInt(PUBLIC_KEY_STRING)
  val KEY_PAIR: ECKeyPair = ECKeyPair(PRIVATE_KEY, PUBLIC_KEY)
  val CREDENTIALS: Credentials = Credentials.create(KEY_PAIR)

end SampleKeys

