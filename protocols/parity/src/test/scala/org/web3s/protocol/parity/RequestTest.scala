package org.web3s.protocol.parity

import org.scalatest.funsuite.AnyFunSuite
import org.web3s.crypto.Wallet
import org.web3s.{Web3sParity, Web3sTrace}
import org.web3s.protocol.core.{DefaultBlockParameter, Web3sServiceRequestJsonTest}
import org.web3s.protocol.core.DefaultBlockParameterName
import org.web3s.protocol.parity.methods.request.{Derivation, TraceFilter}
import org.web3s.utils.EthBigInt

import scala.util.Try

class RequestTest extends AnyFunSuite :

  private val web3sServiceRequestJsonTest = new Web3sServiceRequestJsonTest

  val parity: Parity[Try] = new Web3sParity(web3sServiceRequestJsonTest)
  val trace: Trace[Try] = new Web3sTrace(web3sServiceRequestJsonTest)

  test("ParityAllAccountsInfo") {
    parity.parityAllAccountsInfo
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_allAccountsInfo","params":[],"id":1}""")
  }

  test("ParityChangePassword") {
    parity.parityChangePassword("0x407d73d8a49eeb85d32cf465507dd71d507100c1", "hunter2", "bazqux5")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_changePassword","params":["0x407d73d8a49eeb85d32cf465507dd71d507100c1","hunter2","bazqux5"],"id":1}""")
  }


  test("ParityDeriveAddressHash") {
    val hashType = Derivation.createDerivationHash("0x2547ea3382099c7c76d33dd468063b32d41016aacb02cbd51ebc14ff5d2b6a43", "hard")
    parity.parityDeriveAddressHash("0x407d73d8a49eeb85d32cf465507dd71d507100c1", "hunter2", hashType, false)
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_deriveAddressHash","params":["0x407d73d8a49eeb85d32cf465507dd71d507100c1","hunter2",{"hash":"0x2547ea3382099c7c76d33dd468063b32d41016aacb02cbd51ebc14ff5d2b6a43","type":"hard"},false],"id":1}""")
  }


  test("ParityDeriveAddressIndex") {
    val indexType = List(
      Derivation.createDerivationIndex(1, "hard"),
      Derivation.createDerivationIndex(2, "soft")
    )
    parity.parityDeriveAddressIndex("0x407d73d8a49eeb85d32cf465507dd71d507100c1", "hunter2", indexType, false)
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_deriveAddressIndex","params":["0x407d73d8a49eeb85d32cf465507dd71d507100c1","hunter2",[{"index":1,"type":"hard"},{"index":2,"type":"soft"}],false],"id":1}""")
  }

  test("ParityExportAccount") {
    parity.parityExportAccount("0x407d73d8a49eeb85d32cf465507dd71d507100c1", "hunter2")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_exportAccount","params":["0x407d73d8a49eeb85d32cf465507dd71d507100c1","hunter2"],"id":1}""")
  }


  test("ParityGetDappAddresses") {
    parity.parityGetDappAddresses("web")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_getDappAddresses","params":["web"],"id":1}""")
  }


  test("ParityGetDefaultDappAddress") {
    parity.parityGetDappDefaultAddress("web")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_getDappDefaultAddress","params":["web"],"id":1}""")
  }


  test("parityGetNewDappsAddresses") {
    parity.parityGetNewDappsAddresses
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_getNewDappsAddresses","params":[],"id":1}""")
  }

  test("ParityGetNewDappsDefaultAddress") {
    parity.parityGetNewDappsDefaultAddress
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_getNewDappsDefaultAddress","params":[],"id":1}""")
  }


  test("AllAccountsInfo") {
    parity.parityAllAccountsInfo
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_allAccountsInfo","params":[],"id":1}""")
  }


  test("ParityImportGethAccounts") {
    parity.parityImportGethAccounts(List("0x407d73d8a49eeb85d32cf465507dd71d507100c1"))
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_importGethAccounts","params":[["0x407d73d8a49eeb85d32cf465507dd71d507100c1"]],"id":1}""")
  }


  test("ParityKillAccount") {
    parity.parityKillAccount("0x407d73d8a49eeb85d32cf465507dd71d507100c1", "hunter2")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_killAccount","params":["0x407d73d8a49eeb85d32cf465507dd71d507100c1","hunter2"],"id":1}""")
  }

  test("ParityListAccountsNoAccountOffsetNoBlockTag") {
    val maxQuantityReturned = BigInt(100)
    parity.parityListAccounts(maxQuantityReturned, None, None)
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_listAccounts","params":[100,null],"id":1}""")
  }

  test("ParityListAccountsNoAccountOffsetWithBlockTag") {
    val maxQuantityReturned = BigInt(100)
    val blockParameter = DefaultBlockParameter.valueOf(BigInt(1))
    parity.parityListAccounts(maxQuantityReturned, None, Some(blockParameter))
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_listAccounts","params":[100,null,"0x1"],"id":1}""")
  }

  test("ParityListAccountsWithAccountOffsetWithBlockTag") {
    val maxQuantityReturned = BigInt(100)
    val blockParameter = DefaultBlockParameter.valueOf("LATEST")
    parity.parityListAccounts(
      maxQuantityReturned,
      Some("0x407d73d8a49eeb85d32cf465507dd71d507100c1"),
      Some(blockParameter))

    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_listAccounts","params":[100,"0x407d73d8a49eeb85d32cf465507dd71d507100c1","latest"],"id":1}""")
  }


  test("ParityListAccountsWithAccountOffsetNoBlockTag") {
    val maxQuantityReturned = BigInt(100)
    parity.parityListAccounts(maxQuantityReturned, Some("0x407d73d8a49eeb85d32cf465507dd71d507100c1"), None)
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_listAccounts","params":[100,"0x407d73d8a49eeb85d32cf465507dd71d507100c1"],"id":1}""")
  }

  test("ParityListGethAccounts") {
    parity.parityListGethAccounts
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_listGethAccounts","params":[],"id":1}""")
  }

  test("ParityListRecentDapps") {
    parity.parityListRecentDapps
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_listRecentDapps","params":[],"id":1}""")
  }

  test("ParityNewAccountFromPhrase") {
    parity.parityNewAccountFromPhrase("phrase", "password")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_newAccountFromPhrase","params":["phrase","password"],"id":1}""")
  }

  test("ParityNewAccountFromSecret") {
    parity.parityNewAccountFromSecret("0x1db2c0cf57505d0f4a3d589414f0a0025ca97421d2cd596a9486bc7e2cd2bf8b", "password")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_newAccountFromSecret","params":["0x1db2c0cf57505d0f4a3d589414f0a0025ca97421d2cd596a9486bc7e2cd2bf8b","password"],"id":1}""")
  }

  test("ParityNewAccountFromWallet") {
    val walletFile = Wallet.WalletFile(
      address = Some("0x..."),
      crypto = Wallet.Crypto(
        cipher = "CIPHER",
        ciphertext = "CIPHERTEXT",
        cipherparams = Wallet.CipherParams("IV"),
        kdf = "KDF",
        kdfparams =
          Wallet.ScryptKdfParams(
            dklen = 32,
            n = 1,
            p = 10,
            r = 100,
            salt = "SALT"
          ),
        mac = "MAC"
      ),
      version = 1,
      id = "cab06c9e-79a9-48ea-afc7-d3bdb3a59526"
    )
    parity.parityNewAccountFromWallet(walletFile, "password")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_newAccountFromWallet","params":[{"address":"0x...","id":"cab06c9e-79a9-48ea-afc7-d3bdb3a59526","version":1,"crypto":{"cipher":"CIPHER","ciphertext":"CIPHERTEXT","cipherparams":{"iv":"IV"},"kdf":"KDF","kdfparams":{"dklen":32,"n":1,"p":10,"r":100,"salt":"SALT"},"mac":"MAC"}},"password"],"id":1}""")
  }

  test("ParityRemoveAddress") {
    parity.parityRemoveAddress("0x407d73d8a49eeb85d32cf465507dd71d507100c1")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_removeAddress","params":["0x407d73d8a49eeb85d32cf465507dd71d507100c1"],"id":1}""")
  }

  test("ParitySetAccountMeta") {
    val meta = Map("foo" -> "bar");
    parity.paritySetAccountMeta("0xfc390d8a8ddb591b010fda52f4db4945742c3809", meta)
    web3sServiceRequestJsonTest.verifyResult(
      """{"jsonrpc":"2.0","method":"parity_setAccountMeta","params":["0xfc390d8a8ddb591b010fda52f4db4945742c3809",{"foo":"bar"}],"id":1}""")
  }

  test("ParitySetAccountName") {
    parity.paritySetAccountName("0xfc390d8a8ddb591b010fda52f4db4945742c3809", "Savings")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_setAccountName","params":["0xfc390d8a8ddb591b010fda52f4db4945742c3809","Savings"],"id":1}""")
  }


  test("ParitySetDappAddresses") {
    parity.paritySetDappAddresses("web", List("0x407d73d8a49eeb85d32cf465507dd71d507100c1"))
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_setDappAddresses","params":["web",["0x407d73d8a49eeb85d32cf465507dd71d507100c1"]],"id":1}""")
  }

  test("ParitySetDappDefaultAddress") {
    parity.paritySetDappDefaultAddress("web", "0x407d73d8a49eeb85d32cf465507dd71d507100c1")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_setDappDefaultAddress","params":["web","0x407d73d8a49eeb85d32cf465507dd71d507100c1"],"id":1}""")
  }


  test("ParitySetNewDappsAddresses") {
    parity.paritySetNewDappsAddresses(List("0x407d73d8a49eeb85d32cf465507dd71d507100c1"))
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_setNewDappsAddresses","params":[["0x407d73d8a49eeb85d32cf465507dd71d507100c1"]],"id":1}""")
  }


  test("ParitySetNewDappsDefaultAddress") {
    parity.paritySetNewDappsDefaultAddress("0x407d73d8a49eeb85d32cf465507dd71d507100c1")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_setNewDappsDefaultAddress","params":["0x407d73d8a49eeb85d32cf465507dd71d507100c1"],"id":1}""")
  }


  test("ParityTestPassword") {
    parity.parityTestPassword("0x407d73d8a49eeb85d32cf465507dd71d507100c1", "hunter2")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_testPassword","params":["0x407d73d8a49eeb85d32cf465507dd71d507100c1","hunter2"],"id":1}""")
  }

  test("ParitySignMessage") {
    parity.paritySignMessage("0xc171033d5cbff7175f29dfd3a63dda3d6f8f385e", "password1", "0xbc36789e7a1e281436464229828f817d6612f7b477d66591ff96a9e064bcc98a")
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"parity_signMessage","params":["0xc171033d5cbff7175f29dfd3a63dda3d6f8f385e","password1","0xbc36789e7a1e281436464229828f817d6612f7b477d66591ff96a9e064bcc98a"],"id":1}""")
  }

  test("TraceCall") {
    import org.web3s.protocol.core.methods.request.Transaction
    import org.web3s.utils.Numeric
    val transaction =
      Transaction.createFunctionCallTransaction(
        "0xc171033d5cbff7175f29dfd3a63dda3d6f8f385e",
        BigInt(1),
        Numeric.toBigInt("0x9184e72a000"),
        Numeric.toBigInt("0x76c0"),
        "0xb60e8dd61c5d32be8058bb8eb970870f07233155",
        Numeric.toBigInt("0x9184e72a"),
        "0xd46e8dd67c5d32be8d46e8dd67c5d32be8058bb8eb"
          + "970870f072445675058bb8eb970870f072445675")
    trace.traceCall(transaction, List("trace", "vmTrace", "stateDiff"),DefaultBlockParameterName.LATEST)
    web3sServiceRequestJsonTest.verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"trace_call\","
      + "\"params\":["
      + "{\"from\":\"0xc171033d5cbff7175f29dfd3a63dda3d6f8f385e\","
      + "\"to\":\"0xb60e8dd61c5d32be8058bb8eb970870f07233155\","
      + "\"gas\":\"0x76c0\","
      + "\"gasPrice\":\"0x9184e72a000\","
      + "\"value\":\"0x9184e72a\","
      + "\"data\":\"0xd46e8dd67c5d32be8d46e8dd67c5d32be8058bb8eb970870f072445675058bb8eb970870f072445675\","
      + "\"nonce\":\"0x1\"},"
      + "[\"trace\",\"vmTrace\",\"stateDiff\"],"
      + "\"latest\"],"
      + "\"id\":1}")
  }

  test("TraceRawTransaction") {
    trace.traceRawTransaction("0xf869808504e3b292008305499d94781ab1a38837e351bfe1e318c6587766848abffa8084b46300ec26a0b1ffd8f843e08a9dbf0a42b3c7dd5288a48885cd6e3bcdb2609e943d0b0053d4a07bfdb3c12a7cec896bfc2cfc7c346a2cb411e1aca62ad085e8d7abbb6532e128", List("trace", "vmTrace", "stateDiff"))
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"trace_rawTransaction","params":["0xf869808504e3b292008305499d94781ab1a38837e351bfe1e318c6587766848abffa8084b46300ec26a0b1ffd8f843e08a9dbf0a42b3c7dd5288a48885cd6e3bcdb2609e943d0b0053d4a07bfdb3c12a7cec896bfc2cfc7c346a2cb411e1aca62ad085e8d7abbb6532e128",["trace","vmTrace","stateDiff"]],"id":1}""")
  }

  test("TraceReplayTransaction") {
    trace.traceReplayTransaction("0x090a4bbdeb57f15fe252cccc924255855eda45a2d8f65b12ec81f03e2cc33249", List("trace", "vmTrace", "stateDiff"))
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"trace_replayTransaction","params":["0x090a4bbdeb57f15fe252cccc924255855eda45a2d8f65b12ec81f03e2cc33249",["trace","vmTrace","stateDiff"]],"id":1}""")
  }

  test("TraceBlock") {
    trace.traceBlock(DefaultBlockParameterName.LATEST)
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"trace_block","params":["latest"],"id":1}""")
  }

  test("TraceFilter") {

    trace.traceFilter(TraceFilter(DefaultBlockParameterName.EARLIEST,DefaultBlockParameterName.LATEST, List("0xa9bebd4853ce06c3dc1b711bbafa1514ed5b5130"),List("0xB4d9b203d8D16f41916a62DEab83389cF2b7eeCb")))
    web3sServiceRequestJsonTest.verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"trace_filter\","
      + "\"params\":[{\"fromBlock\":\"earliest\",\"toBlock\":\"latest\","
      + "\"fromAddress\":[\"0xa9bebd4853ce06c3dc1b711bbafa1514ed5b5130\"],"
      + "\"toAddress\":[\"0xB4d9b203d8D16f41916a62DEab83389cF2b7eeCb\"]}],"
      + "\"id\":1}")
  }

  test("TraceGet") {
    trace.traceGet("0x090a4bbdeb57f15fe252cccc924255855eda45a2d8f65b12ec81f03e2cc33249", List(BigInt(2),BigInt(0),BigInt(0)))
    web3sServiceRequestJsonTest.verifyResult("""{"jsonrpc":"2.0","method":"trace_get","params":["0x090a4bbdeb57f15fe252cccc924255855eda45a2d8f65b12ec81f03e2cc33249",["0x2","0x0","0x0"]],"id":1}""")
  }

