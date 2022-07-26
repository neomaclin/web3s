package org.web3s.protocol.eea.util

import org.scalatest.funsuite.AnyFunSuite

class RestrictionTest extends AnyFunSuite :
  test("getRestriction") {
    assert(Restriction.RESTRICTED.restriction == "restricted")
    assert(Restriction.UNRESTRICTED.restriction == "unrestricted")
  }

  test("FromString") {
    assert(Restriction.RESTRICTED == Restriction.fromString("restricted"))
    assert(Restriction.UNRESTRICTED == Restriction.fromString("unrestricted"))
  }