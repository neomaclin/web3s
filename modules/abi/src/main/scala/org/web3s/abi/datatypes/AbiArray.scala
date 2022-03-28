
package org.web3s.abi.datatypes


/** Fixed size array. */
abstract class AbiArray[T <: AbiType[_]](val `type`: Class[T], val value: List[T]) extends AbiType[List[T]]:
//  def this(`type`: String, values: List[T]) = this(AbiTypes.getType(`type`).asInstanceOf[Class[T]], values)
//  def this(`type`: String, values: T*) = this(`type`, List(values))
//  def this(`type`: String) = this(`type`,List.empty[T])
//  def this(`type`: Class[T], values: T*) = this(`type`, List(values))

  override def bytes32PaddedLength: Int = value.foldLeft(0)(_+_.bytes32PaddedLength)
  
  def getComponentType: Class[T] = `type`

 // override def getTypeAsString

//
//  override def equals(o: Any): Boolean = {
//    if (this eq o) return true
//    if (o == null || (getClass ne o.getClass)) return false
//    val array = o.asInstanceOf[Array[_]]
//    if (!(`type` == array.`type`)) return false
//    Objects.equals(value, array.value)
//  }

  override def hashCode: Int =
     31 * `type`.hashCode + (if (value != null) value.hashCode else 0)

end AbiArray
