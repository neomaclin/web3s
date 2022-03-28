
package org.web3s.crypto

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.crypto.SecureRandomUtils.secureRandom

class SecureRandomUtilsTest extends AnyFunSuite :

  test("SecureRandom") {
    secureRandom.nextInt
  }

end SecureRandomUtilsTest
