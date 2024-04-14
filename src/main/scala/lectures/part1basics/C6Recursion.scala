package com.despegar
package lectures.part1basics

import scala.annotation.tailrec

object C6Recursion extends App {

  private def factorial(n: Int): Int = {
    if (n <= 1) 1
    else {
      println("Computing %s".formatted(n))
      n * factorial(n - 1)
    }
  }

  println(factorial(5))
  // CRASH Stack overflow ->
  //println(factorial(50000))

  private def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factorialHelper(x: Int, accumulator: BigInt): BigInt = {
      if (x <= 1) accumulator
      else factorialHelper(x - 1, x * accumulator) // TAIL REC use recursive call as the LAST expression
    }
    factorialHelper(n, 1)
  }

  println(anotherFactorial(5000))

  /*
    1. concatenate TR
    2. is prime TR
    3. fibonacci TR
   */

  private def concatenate(aString: String, n: Int): String = {
    @tailrec
    def concatenateInternal(aString: String, m: Int, accumulator: String): String = {
      if (m <= 0) accumulator
      else concatenateInternal(aString, m - 1, accumulator + aString)
    }
    concatenateInternal(aString, n, "")
  }
  println(concatenate("hello", 3))

  private def prime(n: Int): Boolean = {
    @tailrec
    def primeUntil(t: Int, isStillPrime: Boolean): Boolean = {
      if(!isStillPrime) false
      else if(t <= 1) true
      else primeUntil(t-1, n % t != 0 && isStillPrime)
    }
    primeUntil(n / 2, true)
  }

  println(prime(11))

  // f(n) = f(n - 1) + f(n - 2)
  private def fibonacci(n: Int): Int = {
    @tailrec
    def fibonacciTailRec(i: Int, accumulatorN1: Int, accumulatorN2: Int): Int = {
      if (i <= 2) accumulatorN1
      else fibonacciTailRec(i-1, accumulatorN1 + accumulatorN2, accumulatorN1)
    }
    fibonacciTailRec(n, 1, 1)
  }

  println(fibonacci(8))

  //8,1,1
  //7,2,1
  //6,3,2
  //5,5,3
  //4,8,5
  //3,13,8
  //2,21,13
}
