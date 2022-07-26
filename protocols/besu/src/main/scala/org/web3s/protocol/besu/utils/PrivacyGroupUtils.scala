package org.web3s.protocol.besu.utils

import org.web3s.protocol.eea.util.Base64String
import org.web3s.protocol.eea.util.Base64String.*
import org.web3s.rlp.*
import org.web3s.crypto.Hash

object PrivacyGroupUtils:
  def generateLegacyGroup(privateFrom: Base64String, privateFor: List[Base64String]): Base64String =
    val stringList = Base64String.DECODER.decode(privateFrom.asString) :: privateFor.map(_.raw)
    val rlpList: List[RlpType] = stringList.distinct.sortBy(_.hashCode).map(RlpString.apply)
    Base64String(Hash.sha3(Encoder.encode(RlpList(rlpList.toVector))))

