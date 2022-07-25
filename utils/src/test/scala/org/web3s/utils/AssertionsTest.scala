
package org.web3s.utils

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.utils.Assertions.verifyPrecondition

class AssertionsTest extends AnyFunSuite :

  test("VerifyPrecondition") {
    verifyPrecondition(true, "")
  }

  test("VerifyPreconditionFailure") {
    assertThrows[RuntimeException] {
      verifyPrecondition(false, "")
    }
  }
