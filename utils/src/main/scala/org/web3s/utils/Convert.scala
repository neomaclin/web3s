
package org.web3s.utils

/** Ethereum unit conversion functions. */
object Convert:

  def fromWei(number: String, unit: Convert.Unit): BigDecimal = fromWei(BigDecimal(number), unit)

  def fromWei(number: BigDecimal, unit: Convert.Unit): BigDecimal = number / unit.weiFactor

  def toWei(number: String, unit: Convert.Unit): BigDecimal = toWei(BigDecimal(number), unit)

  def toWei(number: BigDecimal, unit: Convert.Unit): BigDecimal = number * unit.weiFactor

  enum Unit(name: String, factor: Int):
    def weiFactor: BigDecimal = java.math.BigDecimal.TEN.pow(factor)
    case WEI extends Unit("wei", 0)
    case KWEI extends Unit("kwei", 3)
    case MWEI extends Unit("mwei", 6)
    case GWEI extends Unit("gwei", 9)
    case SZABO extends Unit("szabo", 12)
    case FINNEY extends Unit("finney", 15)
    case ETHER extends Unit("ether", 18)
    case KETHER extends Unit("kether", 21)
    case METHER extends Unit("mether", 24)
    case GETHER extends Unit("gether", 27)
  end Unit
  
  object Unit:
    
    def fromString(name:String): Unit = Unit.values.find(_.toString.toLowerCase == name.toLowerCase).get
  
  end Unit
  

end Convert

