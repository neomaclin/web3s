package org.web3s.crypto

import org.scalatest.funsuite.FixtureAsyncFunSuite

import fs2.{Stream, hash, text}
import fs2.io.file.{Files, Path}

import cats.effect.{ Resource, IO }
import cats.effect.testing.scalatest._
import org.web3s.utils.*
import org.web3s.crypto.SampleKeys


class WalletUtilsTest extends FixtureAsyncFunSuite with CatsResourceIO[Path] with AsyncIOSpec :

  override val resource: Resource[IO, Path] = Resource.make(Files[IO].createTempDirectory)(Files[IO].deleteRecursively(_))

  test("GenerateBip44Wallets") { tempDir =>
    for {
      wallet <- WalletUtils.generateBip44Wallet[IO](SampleKeys.PASSWORD, tempDir)
      seed <- IO(MnemonicUtils.generateSeed(wallet.mnemonic, Some(SampleKeys.PASSWORD)))
      masterKeypair <- IO(Bip32ECKeyPair.generateKeyPair(seed))
      bip44Keypair <- IO(WalletUtils.generateBip44KeyPair(masterKeypair))
      credentials <- IO(Credentials.create(bip44Keypair.toECKeyPair))
    } yield assert(credentials == WalletUtils.loadBip44Credentials(SampleKeys.PASSWORD, wallet.mnemonic))
  }

  test("GenerateBip39Wallets") { tempDir =>
    for
      wallet <- WalletUtils.generateBip39Wallet[IO](SampleKeys.PASSWORD, tempDir)
      seed <- IO(MnemonicUtils.generateSeed(wallet.mnemonic, Some(SampleKeys.PASSWORD)))
      credentials <- IO(Credentials.create(ECKeyPair.create(Hash.sha256(seed))))
    yield assert(credentials == WalletUtils.loadBip39Credentials(SampleKeys.PASSWORD, wallet.mnemonic))
  }

  test("GenerateBip39WalletFromMnemonic") { tempDir =>
    for
      wallet <- WalletUtils.generateBip39WalletFromMnemonic[IO](SampleKeys.PASSWORD, SampleKeys.MNEMONIC, tempDir)
      seed <- IO(MnemonicUtils.generateSeed(wallet.mnemonic, Some(SampleKeys.PASSWORD)))
      credentials <- IO(Credentials.create(ECKeyPair.create(Hash.sha256(seed))))
    yield assert(credentials == WalletUtils.loadBip39Credentials(SampleKeys.PASSWORD, wallet.mnemonic))
  }

  test("GenerateFullNewWalletFile") { tempDir =>
    WalletUtils.generateFullNewWalletFile[IO](SampleKeys.PASSWORD, tempDir).flatMap(testGeneratedNewWalletFile)
  }

  test("GenerateNewWalletFile") { tempDir =>
    WalletUtils.generateNewWalletFile[IO](SampleKeys.PASSWORD, tempDir).flatMap(testGeneratedNewWalletFile)
  }

  test("GenerateLightNewWalletFile") { tempDir =>
    WalletUtils.generateLightNewWalletFile[IO](SampleKeys.PASSWORD, tempDir).flatMap(testGeneratedNewWalletFile)
  }

  private def testGeneratedNewWalletFile(path: Path):IO[org.scalatest.compatible.Assertion]  =
    WalletUtils
      .loadCredentials[IO](SampleKeys.PASSWORD, path)
      .map(cred => assert(cred.nonEmpty))


  test("GenerateFullWalletFillWithKeyPair") { tempDir =>
    WalletUtils.generateWalletFile[IO](SampleKeys.PASSWORD, SampleKeys.KEY_PAIR, tempDir, true).flatMap(testGenerateWalletFile)
  }

  test("GenerateLightWalletFileWithKeyPair") { tempDir =>
    WalletUtils.generateWalletFile[IO](SampleKeys.PASSWORD, SampleKeys.KEY_PAIR, tempDir, false).flatMap(testGenerateWalletFile)
  }

  private def testGenerateWalletFile(file: Path):IO[org.scalatest.compatible.Assertion] =
    WalletUtils
      .loadCredentials[IO](SampleKeys.PASSWORD, file)
      .map(cred => assert(cred.contains(SampleKeys.CREDENTIALS)))

  private def testGenerateWalletFileNameString(fileName: String):IO[org.scalatest.compatible.Assertion]  =
    WalletUtils
      .loadCredentials[IO](SampleKeys.PASSWORD, fileName)
      .map(cred => assert(cred.contains(SampleKeys.CREDENTIALS)))

  test("LoadCredentialsFromExistingFile") { _ =>
    Files[IO]
      .currentWorkingDirectory
      .map(_ / "protocols" / "core" / "src" / "test" / "resources" / "keyfiles" / "UTC--2016-11-03T05-55-06.340672473Z--ef678007d18427e6022059dbc264f27507cd1ffc")
      .flatMap(testGenerateWalletFile)
  }

  test("LoadCredentialsFromExistingFileNameString") { _ =>
    Files[IO]
      .currentWorkingDirectory
      .map(_ / "protocols" / "core" / "src" / "test" / "resources" / "keyfiles" / "UTC--2016-11-03T05-55-06.340672473Z--ef678007d18427e6022059dbc264f27507cd1ffc")
      .map(_.toString)
      .flatMap(testGenerateWalletFileNameString)
  }

  //
  //  @Disabled // enable if users need to work with MyEtherWallet
  //  @Test
  //  public void testLoadCredentialsMyEtherWallet() throws Exception {
  //    Credentials credentials =
  //      WalletUtils.loadCredentials(
  //        PASSWORD,
  //        new File(
  //          WalletUtilsTest.
  //      class
  //      .getResource(
  //      "/keyfiles/"
  //        + "UTC--2016-11-03T07-47-45."
  //        + "988Z--4f9c1a1efaa7d81ba1cabf07f2c3a5ac5cf4f818")
  //      .getFile()
  //    ) );
  //
  //    assertEquals(
  //      credentials,
  //      (Credentials.create(
  //        "6ca4203d715e693279d6cd9742ad2fb7a3f6f4abe27a64da92e0a70ae5d859c9")));
  //  }
  //
  //
  //  @Test
  //  public void testGetDefaultKeyDirectory() {
  //    assertTrue(
  //      WalletUtils.getDefaultKeyDirectory("Mac OS X")
  //        .endsWith(
  //          String.format(
  //            "%sLibrary%sEthereum", File.separator, File.separator)));
  //    assertTrue(
  //      WalletUtils.getDefaultKeyDirectory("Windows")
  //        .endsWith(String.format("%sEthereum", File.separator)));
  //    assertTrue(
  //      WalletUtils.getDefaultKeyDirectory("Linux")
  //        .endsWith(String.format("%s.ethereum", File.separator)));
  //  }
  //
  //  @Test
  //  public void testGetTestnetKeyDirectory() {
  //    assertTrue(
  //      WalletUtils.getMainnetKeyDirectory()
  //        .endsWith(String.format("%skeystore", File.separator)));
  //    assertTrue(
  //      WalletUtils.getTestnetKeyDirectory()
  //        .endsWith(
  //          String.format(
  //            "%stestnet%skeystore", File.separator, File.separator)));
  //    assertTrue(
  //      WalletUtils.getRinkebyKeyDirectory()
  //        .endsWith(
  //          String.format(
  //            "%srinkeby%skeystore", File.separator, File.separator)));
  //  }
  //

  test("IsValidPrivateKey") { _ =>
    IO {
      assert(WalletUtils.isValidPrivateKey(SampleKeys.PRIVATE_KEY_STRING));
      assert(WalletUtils.isValidPrivateKey(Numeric.prependHexPrefix(SampleKeys.PRIVATE_KEY_STRING)))

      assert(!WalletUtils.isValidPrivateKey(""))
      assert(!WalletUtils.isValidPrivateKey(SampleKeys.PRIVATE_KEY_STRING + "a"))
      assert(!WalletUtils.isValidPrivateKey(SampleKeys.PRIVATE_KEY_STRING.substring(1)))
    }
  }

  test("IsValidAddress") { _ =>
    IO {
      assert(WalletUtils.isValidAddress(SampleKeys.ADDRESS));
      assert(WalletUtils.isValidAddress(SampleKeys.ADDRESS_NO_PREFIX))

      assert(!WalletUtils.isValidAddress(""))
      assert(!WalletUtils.isValidAddress(SampleKeys.ADDRESS + 'a'))
      assert(!WalletUtils.isValidAddress(SampleKeys.ADDRESS.substring(1)))
    }
  }
