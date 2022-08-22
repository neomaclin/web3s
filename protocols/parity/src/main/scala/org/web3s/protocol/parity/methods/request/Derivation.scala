package org.web3s.protocol.parity.methods.request

final case class Derivation(index: Option[Int],
                            hash: Option[String],
                            `type`: String)

object Derivation:
  def createDerivationHash(hash: String, `type`: String) = Derivation(None, Some(hash), `type`)
  def createDerivationIndex(index: Integer, `type`: String) = Derivation(Some(index), None, `type`)