package com.despegar
package lectures.part1basics

import scala.annotation.tailrec

object C4Functions extends App {

  private def aFunction(a: String, b :Int): String = a + " " + b

  println(aFunction("hello", 3))

  private def aParameterLess(): Int = 42
  println(aParameterLess())
  // TODO println(aParameterLess)

  private def aRepeatedFunction(aString: String, n: Int): String = {
    if(n == 1) aString
    else aString + aRepeatedFunction(aString, n - 1)
  }
  println(aRepeatedFunction("hello", 3))

  def sideEffectFunction(a: String): Unit = {
    println(a)
  }

  def bigFunction(a: String): String = {
    def littleFunction(a: String): String = {
      a
    }
    littleFunction(a)
  }

  /*
   * 1. greeting name int "Hello my name is $name and are $ages years old"
   * 2. factorial 1 * 2 * 3 ... n
   * 3. A Fibonacci f(1) f(2) f(n) = f(n - 1) + f(n - 2)
   * 4. Test if number is prime
   */

  private def greeting(name: String, age: Int): String = {
    "Hello my name is %s and are %s years old".formatted(name, age)
  }
  println(greeting("Christian", 39))

  private def factorial(n: Int): Int = {
    if (n == 1) 1 else n * factorial(n-1)
  }
  println(factorial(5))

  private def fibonacci(n: Int): Int = {
    if (n <= 2) 1
    else fibonacci(n - 1) + fibonacci(n - 2)
  }
  println(fibonacci(8))

  private def prime(n: Int): Boolean = {
    @tailrec // TODO not in tail position
    def primeUntil(t: Int): Boolean = {
      if(t <= 1) true
      else n % t != 0 && primeUntil(t-1)
    }
    primeUntil(n / 2)
  }
  println(prime(11))
  println(prime(37))
  println(prime(2003))
  println(prime(37 * 17))

}
