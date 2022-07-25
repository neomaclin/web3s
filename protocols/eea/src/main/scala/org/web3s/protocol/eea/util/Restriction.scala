package org.web3s.protocol.eea.util

enum Restriction(val restriction: String):
  case RESTRICTED extends Restriction("restricted")
  case UNRESTRICTED extends Restriction("unrestricted")
