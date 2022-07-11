package org.web3s.ens

import org.web3s.tx.ChainIdLong
import org.web3s.ens.exceptions.EnsResolutionException
object Contracts:
  val MAINNET = "0x00000000000C2E074eC69A0dFb2997BA6C7d2e1e"
  val ROPSTEN = "0x00000000000C2E074eC69A0dFb2997BA6C7d2e1e"
  val RINKEBY = "0x00000000000C2E074eC69A0dFb2997BA6C7d2e1e"
  val GOERLI = "0x00000000000C2E074eC69A0dFb2997BA6C7d2e1e"

  def resolveRegistryContract(chainId: String): String =
    chainId.toLong match
      case ChainIdLong.MAINNET => MAINNET
      case ChainIdLong.ROPSTEN => ROPSTEN
      case ChainIdLong.RINKEBY => RINKEBY
      case ChainIdLong.GOERLI => GOERLI
      case _ => throw new EnsResolutionException("Unable to resolve ENS registry contract for network id: " + chainId)

