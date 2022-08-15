package org.web3s

import cats.MonadThrow
import cats.syntax.functor.*
import org.web3s.utils.EthBigInt
import org.web3s.protocol.core.Request
import org.web3s.crypto.Credentials
import org.web3s.protocol.core.*
import org.web3s.protocol.besu.Besu
import org.web3s.protocol.core.methods.response.*
import org.web3s.protocol.besu.methods.response.*
import org.web3s.protocol.besu.methods.response.model.*
import org.web3s.protocol.besu.methods.response.privacy.{PrivFindPrivacyGroup, PrivGetPrivateTransaction, *}
import org.web3s.protocol.core.methods.request
import org.web3s.protocol.eea.util.Base64String
import org.web3s.services.Web3sService

class Web3sBesu[F[_]: MonadThrow](using services: Web3sService[F]) extends Besu[F]:

  import io.circe._
  import io.circe.syntax._
  import io.circe.generic.auto._
  import org.web3s.protocol.core.methods.request.encoder.given
  def minerStart: F[MinerStartResponse] =
    services.fetch[Unit](Request(method = "miner_start")).map(MinerStartResponse.apply)

  def minerStop: F[BooleanResponse] =
    services.fetch[Boolean](Request(method = "miner_stop")).map(BooleanResponse.apply)


  def cliqueDiscard(addressStr: String): F[BooleanResponse]=
    services.fetch[Boolean](Request(method = "clique_discard",params = List(addressStr.asJson))).map(BooleanResponse.apply)

  def cliqueGetSigners(defaultBlockParameter: DefaultBlockParameter): F[EthAccounts]=
    services.fetch[Seq[String]](Request(method = "clique_getSigners",params = List(defaultBlockParameter.asJson))).map(EthAccounts.apply)

  def cliqueGetSignersAtHash(blockHash: String): F[EthAccounts]=
    services.fetch[Seq[String]](Request(method = "clique_getSignersAtHash",params = List(blockHash.asJson))).map(EthAccounts.apply)

  def cliquePropose(address: String, signerAddition: Boolean): F[BooleanResponse]=
    services.fetch[Boolean](Request(method = "clique_propose")).map(BooleanResponse.apply)

  def cliqueProposals: F[BesuEthAccountsMapResponse]=
    services.fetch[Map[String, Boolean]](Request(method = "clique_proposals")).map(BesuEthAccountsMapResponse.apply)

  def debugTraceTransaction(transactionHash: String, options: Map[String, Boolean]): F[BesuFullDebugTraceResponse]=
    services.fetch[BesuFullDebugTraceResponse.FullDebugTraceInfo](Request(method = "debug_traceTransaction")).map(BesuFullDebugTraceResponse.apply)

  def ibftDiscardValidatorVote(address: String): F[BooleanResponse]=
    services.fetch[Boolean](Request(method = "ibft_discardValidatorVote")).map(BooleanResponse.apply)


  def ibftGetPendingVotes: F[BesuEthAccountsMapResponse] =
    services.fetch[Map[String, Boolean]](Request(method = "ibft_getPendingVotes")).map(BesuEthAccountsMapResponse.apply)

  def ibftGetSignerMetrics: F[BesuSignerMetrics]=
    services.fetch[List[BesuSignerMetrics.BesuSignerMetric]](Request(method = "ibft_getSignerMetrics")).map(BesuSignerMetrics.apply)


  def ibftGetValidatorsByBlockNumber(defaultBlockParameter: DefaultBlockParameter): F[EthAccounts]=
    services.fetch[Seq[String]](Request(method = "ibft_getValidatorsByBlockNumber")).map(EthAccounts.apply)


  def ibftGetValidatorsByBlockHash(blockHash: String): F[EthAccounts]=
    services.fetch[Seq[String]](Request(method = "ibft_getValidatorsByBlockHash")).map(EthAccounts.apply)


  def ibftProposeValidatorVote(address: String, validatorAddition: Boolean): F[BooleanResponse]=
    services.fetch[Boolean](Request(method = "ibft_proposeValidatorVote")).map(BooleanResponse.apply)


  def privGetTransactionCount(address: String, privacyGroupId: Base64String): F[EthGetTransactionCount] =
    services.fetch[String](Request(method = "priv_getTransactionCount")).map(EthGetTransactionCount.apply)

  def privGetPrivateTransaction(transactionHash: String): F[PrivGetPrivateTransaction] =
    services.fetch[PrivGetPrivateTransaction.PrivateTransaction](Request(method = "priv_getPrivateTransaction")).map(PrivGetPrivateTransaction.apply)


  def privDistributeRawTransaction(signedTransactionData: String): F[PrivateEnclaveKey]=
    services.fetch[String](Request(method = "priv_distributeRawTransaction")).map(PrivateEnclaveKey.apply)


  def privGetPrivacyPrecompileAddress: F[PrivGetPrivacyPrecompileAddress]=
    services.fetch[String](Request(method = "priv_getPrivacyPrecompileAddress")).map(PrivGetPrivacyPrecompileAddress.apply)


  def privCreatePrivacyGroup(addresses: List[Base64String], name: String, description: String): F[PrivCreatePrivacyGroup]=
    services.fetch[Base64String](Request(method = "priv_createPrivacyGroup")).map(PrivCreatePrivacyGroup.apply)


//  def privOnChainSetGroupLockState(privacyGroupId: Base64String, credentials: Credentials, enclaveKey: Base64String, lock: Boolean): F[EthSendTransaction]=
//    services.fetch[String](Request(method = "web3_clientVersion")).map(Web3ClientVersion.apply)
//
//  def privOnChainAddToPrivacyGroup(privacyGroupId: Base64String, credentials: Credentials, enclaveKey: Base64String, participants: List[Base64String]): F[EthSendTransaction]=
//    services.fetch[String](Request(method = "web3_clientVersion")).map(Web3ClientVersion.apply)
//
//
//  def privOnChainCreatePrivacyGroup(privacyGroupId: Base64String, credentials: Credentials, enclaveKey: Base64String, participants: List[Base64String]): F[EthSendTransaction]=
//    services.fetch[String](Request(method = "web3_clientVersion")).map(Web3ClientVersion.apply)
//
//
//  def privOnChainRemoveFromPrivacyGroup(privacyGroupId: Base64String, credentials: Credentials, enclaveKey: Base64String, participant: Base64String): F[EthSendTransaction]=
//    services.fetch[String](Request(method = "web3_clientVersion")).map(Web3ClientVersion.apply)
//

  def privOnChainFindPrivacyGroup(addresses: List[Base64String]): F[PrivFindPrivacyGroup]=
    services.fetch[List[PrivFindPrivacyGroup.PrivacyGroup]](Request(method = "privx_findOnChainPrivacyGroup")).map(PrivFindPrivacyGroup.apply)


  def privFindPrivacyGroup(addresses: List[Base64String]): F[PrivFindPrivacyGroup]=
    services.fetch[List[PrivFindPrivacyGroup.PrivacyGroup]](Request(method = "priv_findPrivacyGroup")).map(PrivFindPrivacyGroup.apply)


  def privDeletePrivacyGroup(privacyGroupId: Base64String): F[BooleanResponse]=
    services.fetch[Boolean](Request(method = "priv_deletePrivacyGroup")).map(BooleanResponse.apply)


  def privGetTransactionReceipt(transactionHash: String): F[PrivGetTransactionReceipt]=
    services.fetch[PrivateTransactionReceipt](Request(method = "priv_getTransactionReceipt")).map(PrivGetTransactionReceipt.apply)


  def privGetCode(privacyGroupId: String, address: String, defaultBlockParameter: DefaultBlockParameter): F[EthGetCode]=
    services.fetch[String](Request(method = "priv_getCode")).map(EthGetCode.apply)


  def privCall(privacyGroupId: String, transaction: request.Transaction, defaultBlockParameter: DefaultBlockParameter): F[EthCall]=
    services.fetch[String](Request(method = "priv_call")).map(EthCall.apply)


  def privGetLogs(privacyGroupId: String, ethFilter: request.EthFilter): F[EthLog]=
    services.fetch[List[EthLog.LogResult]](Request(method = "priv_getLogs")).map(EthLog.apply)


  def privNewFilter(privacyGroupId: String, ethFilter: request.EthFilter): F[EthFilter]=
    services.fetch[EthBigInt](Request(method = "priv_newFilter")).map(EthFilter.apply)


  def privUninstallFilter(privacyGroupId: String, filterId: String): F[EthUninstallFilter]=
    services.fetch[Boolean](Request(method = "priv_uninstallFilter")).map(EthUninstallFilter.apply)


  def privGetFilterChanges(privacyGroupId: String, filterId: String): F[EthLog]=
    services.fetch[List[EthLog.LogResult]](Request(method = "priv_getFilterChanges")).map(EthLog.apply)


  def privGetFilterLogs(privacyGroupId: String, filterId: String): F[EthLog] =
    services.fetch[List[EthLog.LogResult]](Request(method = "priv_getFilterLogs")).map(EthLog.apply)



