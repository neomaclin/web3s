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


class Web3sParity[F[_] : MonadThrow](using services: Web3sService[F]) extends Parity[F] :
  import io.circe._
  import io.circe.syntax._
  import io.circe.generic.auto._
  def parityAllAccountsInfo: F[ParityAllAccountsInfo] =
    services.fetch[Map[String, ParityAllAccountsInfo.AccountsInfo]](Request(method = "parity_allAccountsInfo")).map(ParityAllAccountsInfo.apply)

  def parityChangePassword(accountId: String, oldPassword: String, newPassword: String): F[BooleanResponse] =
    services.fetch[Boolean](Request(method = "parity_changePassword", params = List(accountId,oldPassword,newPassword).map(_.asJson))).map(BooleanResponse.apply)


  def parityDeriveAddressHash(accountId: String, password: String, hashType: Derivation, toSave: Boolean): F[ParityDeriveAddress] =
    services.fetch[String](Request(method = "parity_deriveAddressHash",params = List(accountId.asJson,password.asJson,hashType.asJson,toSave.asJson))).map(ParityDeriveAddress.apply)


  def parityDeriveAddressIndex(accountId: String, password: String, indicesType: List[Derivation], toSave: Boolean): F[ParityDeriveAddress] =
    services.fetch[String](Request(method = "parity_deriveAddressIndex",params = List(accountId.asJson,password.asJson,indicesType.asJson,toSave.asJson))).map(ParityDeriveAddress.apply)


  def parityExportAccount(accountId: String, password: String): F[ParityExportAccount] =
    services.fetch[WalletFile](Request(method = "parity_exportAccount", params = List(accountId,password).map(_.asJson))).map(ParityExportAccount.apply)


  def parityGetDappAddresses(dAppId: String): F[ParityAddressesResponse] =
    services.fetch[List[String]](Request(method = "parity_getDappAddresses", params = List(dAppId.asJson))).map(ParityAddressesResponse.apply)


  def parityGetDappDefaultAddress(dAppId: String): F[ParityDefaultAddressResponse] =
    services.fetch[String](Request(method = "parity_getDappDefaultAddress", params = List(dAppId.asJson))).map(ParityDefaultAddressResponse.apply)


  def parityGetNewDappsAddresses: F[ParityAddressesResponse] =
    services.fetch[List[String]](Request(method = "parity_getNewDappsAddresses")).map(ParityAddressesResponse.apply)


  def parityGetNewDappsDefaultAddress: F[ParityDefaultAddressResponse] =
    services.fetch[String](Request(method = "parity_getNewDappsDefaultAddress")).map(ParityDefaultAddressResponse.apply)


  def parityImportGethAccounts(gethAddresses: List[String]): F[ParityAddressesResponse] =
    services.fetch[List[String]](Request(method = "parity_importGethAccounts",params = gethAddresses.map(_.asJson))).map(ParityAddressesResponse.apply)


  def parityKillAccount(accountId: String, password: String): F[BooleanResponse] =
    services.fetch[Boolean](Request(method = "parity_killAccount", params = List(accountId,password).map(_.asJson))).map(BooleanResponse.apply)


  def parityListAccounts(quantity: BigInt, accountId: String, blockParameter: DefaultBlockParameter): F[ParityAddressesResponse] =
    services.fetch[List[String]](Request(method = "parity_listAccounts")).map(ParityAddressesResponse.apply)


  def parityListGethAccounts: F[ParityAddressesResponse] =
    services.fetch[List[String]](Request(method = "parity_listGethAccounts")).map(ParityAddressesResponse.apply)


  def parityListRecentDapps: F[ParityListRecentDapps] =
    services.fetch[List[String]](Request(method = "parity_listRecentDapps")).map(ParityListRecentDapps.apply)


  def parityNewAccountFromPhrase(phrase: String, password: String): F[NewAccountIdentifier] =
    services.fetch[String](Request(method = "parity_newAccountFromPhrase")).map(NewAccountIdentifier.apply)


  def parityNewAccountFromSecret(secret: String, password: String): F[NewAccountIdentifier] =
    services.fetch[String](Request(method = "parity_newAccountFromSecret")).map(NewAccountIdentifier.apply)


  def parityNewAccountFromWallet(walletFile: WalletFile, password: String): F[NewAccountIdentifier] =
    services.fetch[String](Request(method = "parity_newAccountFromWallet")).map(NewAccountIdentifier.apply)


  def parityRemoveAddress(accountId: String): F[BooleanResponse] =
    services.fetch[Boolean](Request(method = "parity_RemoveAddress")).map(BooleanResponse.apply)


  def paritySetAccountMeta(accountId: String, metadata: Map[String, AnyRef]): F[BooleanResponse] =
    services.fetch[Boolean](Request(method = "parity_setAccountMeta")).map(BooleanResponse.apply)


  def paritySetAccountName(address: String, name: String): F[BooleanResponse] =
    services.fetch[Boolean](Request(method = "parity_setAccountName")).map(BooleanResponse.apply)


  def paritySetDappAddresses(dAppId: String, availableAccountIds: List[String]): F[BooleanResponse] =
    services.fetch[Boolean](Request(method = "parity_setDappAddresses")).map(BooleanResponse.apply)


  def paritySetDappDefaultAddress(dAppId: String, defaultAddress: String): F[BooleanResponse] =
    services.fetch[Boolean](Request(method = "parity_setDappDefaultAddress")).map(BooleanResponse.apply)


  def paritySetNewDappsAddresses(availableAccountIds: List[String]): F[BooleanResponse] =
    services.fetch[Boolean](Request(method = "parity_setNewDappsAddresses")).map(BooleanResponse.apply)


  def paritySetNewDappsDefaultAddress(defaultAddress: String): F[BooleanResponse] =
    services.fetch[Boolean](Request(method = "parity_setNewDappsDefaultAddress")).map(BooleanResponse.apply)


  def parityTestPassword(accountId: String, password: String): F[BooleanResponse] =
    services.fetch[Boolean](Request(method = "parity_testPassword")).map(BooleanResponse.apply)


  def paritySignMessage(accountId: String, password: String, hexMessage: String): F[PersonalSign] =
    services.fetch[String](Request(method = "parity_signMessage")).map(PersonalSign.apply)

