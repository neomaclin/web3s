//package org.web3s.protocol.parity
//
//import org.web3s.protocol.core.methods.response.*
////
////trait Parity[F[_]]:
////  def parityAllAccountsInfo: F[ParityAllAccountsInfo]
////
////  def parityChangePassword(accountId: String, oldPassword: String, newPassword: String): F[BooleanResponse]
////
////  def parityDeriveAddressHash(accountId: String, password: String, hashType: Derivation, toSave: Boolean): F[ParityDeriveAddress]
////
////  def parityDeriveAddressIndex(accountId: String, password: String, indicesType: List[Derivation], toSave: Boolean): F[ParityDeriveAddress]
////
////  def parityExportAccount(accountId: String, password: String): F[ParityExportAccount]
////
////  def parityGetDappAddresses(dAppId: String): F[ParityAddressesResponse]
////
////  def parityGetDappDefaultAddress(dAppId: String): F[ParityDefaultAddressResponse]
////
////  def parityGetNewDappsAddresses: F[ParityAddressesResponse]
////
////  def parityGetNewDappsDefaultAddress: F[ParityDefaultAddressResponse]
////
////  def parityImportGethAccounts(gethAddresses: ArrayList[String]): F[ParityAddressesResponse]
////
////  def parityKillAccount(accountId: String, password: String): F[BooleanResponse]
////
////  def parityListAccounts(quantity: BigInteger, accountId: String, blockParameter: DefaultBlockParameter): F[ParityAddressesResponse]
////
////  def parityListGethAccounts: F[ParityAddressesResponse]
////
////  def parityListRecentDapps: F[ParityListRecentDapps]
////
////  def parityNewAccountFromPhrase(phrase: String, password: String): F[NewAccountIdentifier]
////
////  def parityNewAccountFromSecret(secret: String, password: String): F[NewAccountIdentifier]
////
////  def parityNewAccountFromWallet(walletFile: WalletFile, password: String): F[NewAccountIdentifier]
////
////  def parityRemoveAddress(accountId: String): F[BooleanResponse]
////
////  def paritySetAccountMeta(accountId: String, metadata: Map[String, AnyRef]): F[BooleanResponse]
////
////  def paritySetAccountName(address: String, name: String): F[BooleanResponse]
////
////  def paritySetDappAddresses(dAppId: String, availableAccountIds: ArrayList[String]): F[BooleanResponse]
////
////  def paritySetDappDefaultAddress(dAppId: String, defaultAddress: String): F[BooleanResponse]
////
////  def paritySetNewDappsAddresses(availableAccountIds: ArrayList[String]): F[BooleanResponse]
////
////  def paritySetNewDappsDefaultAddress(defaultAddress: String): F[BooleanResponse]
////
////  def parityTestPassword(accountId: String, password: String): F[BooleanResponse]
////
////  def paritySignMessage(accountId: String, password: String, hexMessage: String): F[PersonalSign]
