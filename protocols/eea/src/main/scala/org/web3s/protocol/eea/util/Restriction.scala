package org.web3s.protocol.eea.util

enum Restriction(val restriction: String):
  case RESTRICTED extends Restriction("restricted")
  case UNRESTRICTED extends Restriction("unrestricted")

object Restriction:
  def fromString(value: String): Restriction = value.toLowerCase match
    case "restricted" => RESTRICTED
    case "unrestricted" => UNRESTRICTED
    case _ => RESTRICTED