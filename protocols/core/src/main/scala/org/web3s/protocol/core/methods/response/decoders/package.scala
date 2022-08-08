package org.web3s.protocol.core.methods.response


import org.web3s.model.*
import io.circe.Decoder
import io.circe.generic.semiauto._
import cats.syntax.functor._

package decoders:

  import org.web3s.protocol.core.methods.response.*
  import AbiDefinition._

  private given Decoder[EthCompileSolidity.Documentation] = deriveDecoder[EthCompileSolidity.Documentation]

  private given Decoder[AbiDefinition] = deriveDecoder[AbiDefinition]

  private given Decoder[EthCompileSolidity.SolidityInfo] = deriveDecoder[EthCompileSolidity.SolidityInfo]

  given Decoder[EthCompileSolidity.Code] = deriveDecoder[EthCompileSolidity.Code]

  given Decoder[EthBlock.Block] = deriveDecoder[EthBlock.Block]

  private given Decoder[EthLog.Log] = deriveDecoder[EthLog.Log]

  private given Decoder[EthLog.Hash] = deriveDecoder[EthLog.Hash]

  given Decoder[EthLog.LogResult] = List[Decoder[EthLog.LogResult]](
    Decoder[EthLog.Log].widen,
    Decoder[EthLog.Hash].widen,
  ).reduceLeft(_ or _)


  given Decoder[EthTransaction.AccessListObject] = deriveDecoder[EthTransaction.AccessListObject]

  given Decoder[EthTransaction.Transaction] = deriveDecoder[EthTransaction.Transaction]
