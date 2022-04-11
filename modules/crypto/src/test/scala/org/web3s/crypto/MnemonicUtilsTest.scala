
package org.web3s.crypto

import org.bouncycastle.util.encoders.Hex
import org.scalatest.funsuite.AnyFunSuite
import org.web3s.utils.Numeric

class MnemonicUtilsTest extends AnyFunSuite :
//  private val SAMPLE_FILE = "build/resources/test/mnemonics/test-vectors.txt"
//  def data = {
//    val data = Files.lines(Paths.get(SAMPLE_FILE)).collect(Collectors.joining("\n"))
//    val each = data.split("###")
//    val parameters = new util.ArrayList[Nothing]
//    for (part <- each) {
//      parameters.add(Arguments.of(part.trim.split("\n").asInstanceOf[Array[AnyRef]]))
//    }
//    parameters.stream
//  }
//   generateMnemonicShouldGenerateExpectedMnemonicWords(initialEntropy: String, mnemonic: String) = {
//    val actualMnemonic = MnemonicUtils.generateMnemonic(Hex.decode(initialEntropy))
//    assert(mnemonic == actualMnemonic)
//  }
//
// def generateSeedShouldGenerateExpectedSeeds(initialEntropy: String, mnemonic: String, seed: String) = {
//    val actualSeed = MnemonicUtils.generateSeed(mnemonic, "TREZOR")
//    assert(Hex.decode(seed) sameElements  actualSeed)
//  }
//
//   def generateEntropyShouldGenerateExpectedEntropy(initialEntropy: String, mnemonic: String) = {
//    val actualEntropy = MnemonicUtils.generateEntropy(mnemonic)
//    assert(Hex.decode(initialEntropy), actualEntropy)
//  }

  test("ShouldGenerateCorrectEntropyFromMnemonic") { // from https://github.com/trezor/python-mnemonic/blob/master/vectors.json
    assertCorrectEntropy("00000000000000000000000000000000", "abandon abandon abandon abandon abandon abandon abandon abandon abandon abandon " + "abandon about")
    assertCorrectEntropy("7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f7f", "legal winner thank year wave sausage worth useful legal winner thank yellow")
    assertCorrectEntropy("80808080808080808080808080808080", "letter advice cage absurd amount doctor acoustic avoid letter advice cage above")
    assertCorrectEntropy("ffffffffffffffffffffffffffffffff", "zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo zoo wrong")
    assertCorrectEntropy("9e885d952ad362caeb4efe34a8e91bd2", "ozone drill grab fiber curtain grace pudding thank cruise elder eight picnic")
    assertCorrectEntropy("68a79eaca2324873eacc50cb9c6eca8cc68ea5d936f98787c60c7ebc74e6ce7c", "hamster diagram private dutch cause delay private meat slide toddler razor book " + "happy fancy gospel tennis maple dilemma loan word shrug inflict delay " + "length", 64)
    assertCorrectEntropy("23db8160a31d3e0dca3688ed941adbf3", "cat swing flag economy stadium alone churn speed unique patch report train")
    assertCorrectEntropy("8080808080808080808080808080808080808080808080808080808080808080", "letter advice cage absurd amount doctor acoustic avoid letter advice cage absurd" + " amount doctor acoustic avoid letter advice cage absurd amount doctor " + "acoustic bless", 64)
    assertCorrectEntropy("066dca1a2bb7e8a1db2832148ce9933eea0f3ac9548d793112d9a95c9407efad", "all hour make first leader extend hole alien behind guard gospel lava path " + "output census museum junior mass reopen famous sing advance salt reform", 64)
    assertCorrectEntropy("f30f8c1da665478f49b001d94c5fc452", "vessel ladder alter error federal sibling chat ability sun glass valve picture")
    assertCorrectEntropy("c10ec20dc3cd9f652c7fac2f1230f7a3c828389a14392f05", "scissors invite lock maple supreme raw rapid void congress muscle digital " + "elegant little brisk hair mango congress clump", 48)
    assertCorrectEntropy("f585c11aec520db57dd353c69554b21a89b20fb0650966fa0a9d6f74fd989d8f", "void come effort suffer camp survey warrior heavy shoot primary clutch crush " + "open amazing screen patrol group space point ten exist slush involve " + "unfold", 64)
  }

  test("ShouldProduceTheSameMnemonic") {
    val expected = "clinic excuse minimum until indoor flower fun concert inquiry letter audit patrol"
    val actual = MnemonicUtils.generateMnemonic(MnemonicUtils.generateEntropy(expected))
    assert(expected == actual)
  }

  test("ShouldProduceTheSameEntropy") {
    val expected = Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15).map(_.toByte)
    val actual = MnemonicUtils.generateEntropy(MnemonicUtils.generateMnemonic(expected))
    assert(expected sameElements actual)
  }

  test("ShouldThrowOnEmptyMnemonic"){
    assertThrows[IllegalArgumentException] {
      MnemonicUtils.generateEntropy("")
    }
  }

  private def assertCorrectEntropy(expected: String, mnemonic: String): Unit =
    assertCorrectEntropy(expected, mnemonic, 32)

  private def assertCorrectEntropy(expected: String, mnemonic: String, size: Int): Unit =
    assert(expected == Numeric.toHexStringNoPrefixZeroPadded(Numeric.toBigInt(MnemonicUtils.generateEntropy(mnemonic)), size))

end MnemonicUtilsTest
