package org.web3s

import cats.MonadThrow
import cats.syntax.functor._
import org.web3s.services.Web3sService
import org.web3s.crypto.Wallet.WalletFile
import org.web3s.protocol.core.methods.response.*
import org.web3s.protocol.admin.methods.response.*
import org.web3s.protocol.core.{DefaultBlockParameter, Request}
import org.web3s.protocol.parity.methods.response.*
import org.web3s.protocol.parity.methods.request.*
import org.web3s.protocol.parity.Parity


class Web3sParity[F[_] : MonadThrow](services: Web3sService[F]) extends Parity[F] :

  import io.circe._
  import io.circe.syntax._
  import io.circe.generic.auto._
  import org.web3s.protocol.core.methods.request.encoder.given
  import org.web3s.crypto.Wallet.given

  def parityAllAccountsInfo: F[ParityAllAccountsInfo] =
    services.fetch[Map[String, ParityAllAccountsInfo.AccountsInfo]](Request(method = "parity_allAccountsInfo")).map(ParityAllAccountsInfo.apply)

  def parityChangePassword(accountId: String, oldPassword: String, newPassword: String): F[BooleanResponse] =
    val params = List(accountId, oldPassword, newPassword).map(_.asJson)
    val request = Request(method = "parity_changePassword", params)
    services.fetch[Boolean](request).map(BooleanResponse.apply)


  def parityDeriveAddressHash(accountId: String, password: String, hashType: Derivation, toSave: Boolean): F[ParityAddressResponse] =
    val params = List(accountId.asJson, password.asJson, hashType.asJson.dropNullValues, toSave.asJson)
    val request = Request(method = "parity_deriveAddressHash", params)
    services.fetch[String](request).map(ParityAddressResponse.apply)


  def parityDeriveAddressIndex(accountId: String, password: String, indicesType: List[Derivation], toSave: Boolean): F[ParityAddressResponse] =
    val params = List(accountId.asJson, password.asJson, indicesType.map(_.asJson.dropNullValues).asJson, toSave.asJson)
    val request = Request(method = "parity_deriveAddressIndex", params)
    services.fetch[String](request).map(ParityAddressResponse.apply)


  def parityExportAccount(accountId: String, password: String): F[ParityExportAccount] =
    val params = List(accountId, password).map(_.asJson)
    val request = Request(method = "parity_exportAccount", params)
    services.fetch[WalletFile](request).map(ParityExportAccount.apply)


  def parityGetDappAddresses(dAppId: String): F[ParityAddressesResponse] =
    val params = List(dAppId.asJson)
    val request = Request(method = "parity_getDappAddresses", params)
    services.fetch[List[String]](request).map(ParityAddressesResponse.apply)


  def parityGetDappDefaultAddress(dAppId: String): F[ParityAddressResponse] =
    val params = List(dAppId.asJson)
    val request = Request(method = "parity_getDappDefaultAddress", params)
    services.fetch[String](request).map(ParityAddressResponse.apply)


  def parityGetNewDappsAddresses: F[ParityAddressesResponse] =
    services.fetch[List[String]](Request(method = "parity_getNewDappsAddresses")).map(ParityAddressesResponse.apply)


  def parityGetNewDappsDefaultAddress: F[ParityAddressResponse] =
    services.fetch[String](Request(method = "parity_getNewDappsDefaultAddress")).map(ParityAddressResponse.apply)


  def parityImportGethAccounts(gethAddresses: List[String]): F[ParityAddressesResponse] =
    val params = List(gethAddresses.asJson)
    val request = Request(method = "parity_importGethAccounts", params)
    services.fetch[List[String]](request).map(ParityAddressesResponse.apply)


  def parityKillAccount(accountId: String, password: String): F[BooleanResponse] =
    val params = List(accountId, password).map(_.asJson)
    val request = Request(method = "parity_killAccount", params)
    services.fetch[Boolean](request).map(BooleanResponse.apply)


  def parityListAccounts(quantity: BigInt, accountId: Option[String], blockParameter: Option[DefaultBlockParameter]): F[ParityAddressesResponse] =
    val params = List(quantity.intValue.asJson, accountId.asJson) ++ blockParameter.map(_.asJson)
    val request = Request(method = "parity_listAccounts", params)
    services.fetch[List[String]](request).map(ParityAddressesResponse.apply)


  def parityListGethAccounts: F[ParityAddressesResponse] =
    services.fetch[List[String]](Request(method = "parity_listGethAccounts")).map(ParityAddressesResponse.apply)


  def parityListRecentDapps: F[ParityListRecentDapps] =
    services.fetch[List[String]](Request(method = "parity_listRecentDapps")).map(ParityListRecentDapps.apply)


  def parityNewAccountFromPhrase(phrase: String, password: String): F[NewAccountIdentifier] =
    val params = List(phrase, password).map(_.asJson)
    val request = Request(method = "parity_newAccountFromPhrase", params)
    services.fetch[String](request).map(NewAccountIdentifier.apply)


  def parityNewAccountFromSecret(secret: String, password: String): F[NewAccountIdentifier] =
    val params = List(secret, password).map(_.asJson)
    val request = Request(method = "parity_newAccountFromSecret", params)
    services.fetch[String](request).map(NewAccountIdentifier.apply)


  def parityNewAccountFromWallet(walletFile: WalletFile, password: String): F[NewAccountIdentifier] =
    val params = List(walletFile.asJson, password.asJson)
    val request = Request(method = "parity_newAccountFromWallet", params)
    services.fetch[String](request).map(NewAccountIdentifier.apply)


  def parityRemoveAddress(accountId: String): F[BooleanResponse] =
    val params = List(accountId.asJson)
    val request = Request(method = "parity_removeAddress", params)
    services.fetch[Boolean](request).map(BooleanResponse.apply)


  def paritySetAccountMeta[Value: Encoder](accountId: String, metadata: Map[String, Value]): F[BooleanResponse] =
    val params = List(accountId.asJson, metadata.asJson)
    val request = Request(method = "parity_setAccountMeta", params)
    services.fetch[Boolean](request).map(BooleanResponse.apply)

  def paritySetAccountName(address: String, name: String): F[BooleanResponse] =
    val params = List(address, name).map(_.asJson)
    val request = Request(method = "parity_setAccountName", params)
    services.fetch[Boolean](request).map(BooleanResponse.apply)


  def paritySetDappAddresses(dAppId: String, availableAccountIds: List[String]): F[BooleanResponse] =
    val params = List(dAppId.asJson, availableAccountIds.asJson)
    val request = Request(method = "parity_setDappAddresses", params)
    services.fetch[Boolean](request).map(BooleanResponse.apply)


  def paritySetDappDefaultAddress(dAppId: String, defaultAddress: String): F[BooleanResponse] =
    val params = List(dAppId.asJson, defaultAddress.asJson)
    val request = Request(method = "parity_setDappDefaultAddress", params)
    services.fetch[Boolean](request).map(BooleanResponse.apply)


  def paritySetNewDappsAddresses(availableAccountIds: List[String]): F[BooleanResponse] =
    val params = List(availableAccountIds.asJson)
    val request = Request(method = "parity_setNewDappsAddresses", params)
    services.fetch[Boolean](request).map(BooleanResponse.apply)


  def paritySetNewDappsDefaultAddress(defaultAddress: String): F[BooleanResponse] =
    val params = List(defaultAddress.asJson)
    val request = Request(method = "parity_setNewDappsDefaultAddress", params)
    services.fetch[Boolean](request).map(BooleanResponse.apply)


  def parityTestPassword(accountId: String, password: String): F[BooleanResponse] =
    val params = List(accountId, password).map(_.asJson)
    val request = Request(method = "parity_testPassword", params)
    services.fetch[Boolean](request).map(BooleanResponse.apply)


  def paritySignMessage(accountId: String, password: String, hexMessage: String): F[PersonalSign] =
    val params = List(accountId, password, hexMessage).map(_.asJson)
    val request = Request(method = "parity_signMessage", params)
    services.fetch[String](request).map(PersonalSign.apply)

