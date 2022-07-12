package org.web3s.ens

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.ens.exceptions.EnsResolutionException
import org.web3s.tx.ChainIdLong

class ContractsTest extends AnyFunSuite:

  test("ResolveRegistryContract") {
    assert(Contracts.resolveRegistryContract(ChainIdLong.MAINNET + "") == Contracts.MAINNET)
    assert(Contracts.resolveRegistryContract(ChainIdLong.ROPSTEN + "") == Contracts.ROPSTEN)
    assert(Contracts.resolveRegistryContract(ChainIdLong.RINKEBY + "") == Contracts.RINKEBY)
  }

  test("ResolveRegistryContractInvalid") {
    assertThrows[EnsResolutionException] {
      Contracts.resolveRegistryContract(ChainIdLong.NONE + "")
    }
  }