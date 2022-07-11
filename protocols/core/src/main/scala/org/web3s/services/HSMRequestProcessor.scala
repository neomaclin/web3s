package org.web3s.services

import org.web3s.crypto.Sign
import org.web3s.crypto.HSMPass

trait HSMRequestProcessor[F[_]]:
  def callHSM[T <: HSMPass](dataToSign: Array[Byte], pass: T): F[Sign.SignatureData]


