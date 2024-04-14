package com.despegar
package lectures.part3functional

object C27HOFsCurries extends App {

  val superFunction: (Int, (Int, String) => String) => Int => Int = null
  // in receives a func => high order functions or HOFs

  //nTimes(f, n, x)
  def nTimes(f:Int => Int, n: Int, x: Int): Int = {
    if(n <= 0) x
    else nTimes(f, n-1, f(x))
  }

  def plusOne = (x: Int) => x + 1
  println(nTimes(plusOne, 10, 1))

  def nTimesBetter(f:Int => Int, n: Int): Int => Int = {
    if (n <= 0) x => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))
  }

  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(1))

  // curried functions
  val superAdder: Int => Int => Int = x => y => x + y
  val add3 = superAdder(3)
  println(add3(10))
  println(superAdder(3)(10))

  // function with multiple params lists
  def curriedFormatter(c: String)(x: Double): String = c.formatted(x)

  val stdFormat = curriedFormatter("%4.2f")
  val preciseFormat = curriedFormatter("%10.8f")

  println(stdFormat(Math.PI))
  println(preciseFormat(Math.PI))
}
