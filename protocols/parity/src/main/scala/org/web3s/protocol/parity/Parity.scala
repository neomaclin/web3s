package org.web3s.protocol.parity

import org.web3s.crypto.Wallet.WalletFile
import org.web3s.protocol.core.methods.response.*
import org.web3s.protocol.admin.methods.response.*
import org.web3s.protocol.core.DefaultBlockParameter
import org.web3s.protocol.parity.methods.response.*
import org.web3s.protocol.parity.methods.request.*

import io.circe.Encoder

trait Parity[F[_]]:
  def parityAllAccountsInfo: F[ParityAllAccountsInfo]

  def parityChangePassword(accountId: String, oldPassword: String, newPassword: String): F[BooleanResponse]

  def parityDeriveAddressHash(accountId: String, password: String, hashType: Derivation, toSave: Boolean): F[ParityAddressResponse]

  def parityDeriveAddressIndex(accountId: String, password: String, indicesType: List[Derivation], toSave: Boolean): F[ParityAddressResponse]

  def parityExportAccount(accountId: String, password: String): F[ParityExportAccount]

  def parityGetDappAddresses(dAppId: String): F[ParityAddressesResponse]

  def parityGetDappDefaultAddress(dAppId: String): F[ParityAddressResponse]

  def parityGetNewDappsAddresses: F[ParityAddressesResponse]

  def parityGetNewDappsDefaultAddress: F[ParityAddressResponse]

  def parityImportGethAccounts(gethAddresses: List[String]): F[ParityAddressesResponse]

  def parityKillAccount(accountId: String, password: String): F[BooleanResponse]

  def parityListAccounts(quantity: BigInt, accountId: Option[String], blockParameter: Option[DefaultBlockParameter]): F[ParityAddressesResponse]

  def parityListGethAccounts: F[ParityAddressesResponse]

  def parityListRecentDapps: F[ParityListRecentDapps]

  def parityNewAccountFromPhrase(phrase: String, password: String): F[NewAccountIdentifier]

  def parityNewAccountFromSecret(secret: String, password: String): F[NewAccountIdentifier]

  def parityNewAccountFromWallet(walletFile: WalletFile, password: String): F[NewAccountIdentifier]

  def parityRemoveAddress(accountId: String): F[BooleanResponse]

  def paritySetAccountMeta[Value: Encoder](accountId: String, metadata: Map[String, Value]): F[BooleanResponse]

  def paritySetAccountName(address: String, name: String): F[BooleanResponse]

  def paritySetDappAddresses(dAppId: String, availableAccountIds: List[String]): F[BooleanResponse]

  def paritySetDappDefaultAddress(dAppId: String, defaultAddress: String): F[BooleanResponse]

  def paritySetNewDappsAddresses(availableAccountIds: List[String]): F[BooleanResponse]

  def paritySetNewDappsDefaultAddress(defaultAddress: String): F[BooleanResponse]

  def parityTestPassword(accountId: String, password: String): F[BooleanResponse]

  def paritySignMessage(accountId: String, password: String, hexMessage: String): F[PersonalSign]
