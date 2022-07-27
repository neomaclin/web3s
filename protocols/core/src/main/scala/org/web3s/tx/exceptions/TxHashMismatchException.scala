package org.web3s.tx.exceptions

import java.io.IOException

final case class TxHashMismatchException(txHashLocal: String, txHashRemote: String) extends IOException