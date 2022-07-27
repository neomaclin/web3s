package org.web3s.protocol.parity.methods.response

import org.web3s.crypto.Wallet.WalletFile
import org.web3s.protocol.core.Response

opaque type ParityExportAccount = Response[WalletFile]

object ParityExportAccount:
  extension (x: ParityExportAccount)
    def wallet: WalletFile = x.result

  def apply(response: Response[WalletFile]): ParityExportAccount = response

