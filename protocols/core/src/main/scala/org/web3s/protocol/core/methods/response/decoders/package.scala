package org.web3s.protocol.core.methods.response


import org.web3s.model.*
import io.circe.Decoder
import io.circe.generic.semiauto._
import cats.syntax.functor._

package decoders:

  import org.web3s.protocol.core.methods.response.*
  import AbiDefinition.*
  import org.web3s.protocol.core.methods.response.EthBlock.TransactionResult
  import org.web3s.protocol.core.methods.response.EthTransaction.Transaction
  import org.web3s.utils.{EthBigInt, Numeric}

  import scala.util.Try

  private given Decoder[EthCompileSolidity.Documentation] = deriveDecoder[EthCompileSolidity.Documentation]
  private given Decoder[AbiDefinition] = deriveDecoder[AbiDefinition]
  private given Decoder[EthCompileSolidity.SolidityInfo] = deriveDecoder[EthCompileSolidity.SolidityInfo]
  given Decoder[EthCompileSolidity.Code] = deriveDecoder[EthCompileSolidity.Code]
  given Decoder[EthBlock.Block] = deriveDecoder[EthBlock.Block]
  private given Decoder[EthLog.Log] = deriveDecoder[EthLog.Log]
  private given Decoder[EthLog.Hash] = deriveDecoder[EthLog.Hash]
  given Decoder[EthLog.LogResult] = Decoder[EthLog.Log].widen or Decoder[EthLog.Hash].widen
  given Decoder[EthBigInt] = Decoder.decodeString.emapTry(x => Try(Numeric.decodeQuantity(x))).map(EthBigInt.apply)
  given Decoder[EthTransaction.AccessListObject] = deriveDecoder[EthTransaction.AccessListObject]
  given Decoder[EthTransaction.Transaction] = deriveDecoder[EthTransaction.Transaction]
  given Decoder[EthBlock.TransactionResult] = Decoder[EthTransaction.Transaction].widen or Decoder[String].widen
