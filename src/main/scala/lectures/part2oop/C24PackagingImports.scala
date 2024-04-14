package com.despegar
package lectures.part2oop

import playground.{King, Princess => Prin}
// or import playground._

object C24PackagingImports extends App {

  // simple name
  // o import
  // or full qualified name

  // packages are in hierarchy

  // packages objects

  sayHello()

  val a: Int = A_VALUE

  val princess = Prin
  val king = King

  // fqn
  // aliasing

  // defaults
  // java.lang -> String, Object
  // scala -> Int, Nothing, Function
  // scala.Predef -> println, ???

}
