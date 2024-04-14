package com.despegar
package lectures.part1basics

object C2ValuesVariablesTypes extends App {

  // IMMUTABLE
  private val x: Int = 42
  println(x)

  // VALs CAN INFER TYPES
  private val y: Int = 42
  println(y)

  // SEMICOLONS ARE OPTIONAL
  private val aString: String = "a string"
  println(aString)

  // OTHER TYPES
  val aBoolean: Boolean = true
  val aChar: Char = 'C'
  val aShort: Short = 12345
  val aLong: Long = 1234544444444444L
  val aFloat: Float = 2.0f
  val aDouble: Double = 2.0

  // VARIABLES

  private var aVariable: String = "initial"
  println(aVariable)
  aVariable = "other value" // side effects
  println(aVariable)

}
