package org.web3s.utils

import org.scalatest.funsuite.AnyFunSuite

class EnsUtilsTest extends AnyFunSuite :

  test("isEIP3668WhenEmptyOrLessLength") {
    assert(!EnsUtils.isEIP3668(""))
    assert(!EnsUtils.isEIP3668("123456789"))
  }

  test("isEIP3668WhenNotRightPrefix") {
    assert(!EnsUtils.isEIP3668("123456789012"))
  }

  test("isEIP3668WhenSuccess") {
    assert(EnsUtils.isEIP3668(EnsUtils.EIP_3668_CCIP_INTERFACE_ID + "some data"))
  }

  test("getParentWhenSuccess") {
    assert("offchainexample.eth" == EnsUtils.getParent("1.offchainexample.eth"))
    assert("eth" == EnsUtils.getParent("offchainexample.eth"))
  }

  test("getParentWhenUrlIsEmpty") {
    assert(EnsUtils.getParent("").isEmpty)
    assert(EnsUtils.getParent(" ").isEmpty)
  }

  test("getParentWhenUrlWithoutParent") {
    assert(EnsUtils.getParent("parent").isEmpty)
  }
