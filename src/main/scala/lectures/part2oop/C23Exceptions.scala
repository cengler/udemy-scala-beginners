package com.despegar
package lectures.part2oop

object C23Exceptions extends App {

  val x: String = null
  // println(x.length)

  // throw and catch exceptions
  // val aWiredValue: String = throw new NullPointerException()

  // throwable classes extends Throwable class
  // EXCEPTIONS and ERRORS

  // catch
  def getInt(withExceptions: Boolean): Int = {
    if(withExceptions) throw new RuntimeException("No int for you")
    else 42
  }

  val potentialFail = try {
    getInt(true)
  } catch {
    case e: RuntimeException => println("Caught an Runtime exception")
  } finally {
    // NO MATHER WHAT
    // - optional
    // - does not influence the return
    println("finally")
  }

  class MyException extends Exception

  // Exercises
  /*
  1) OOM
  2) SOError
  3) Pocket Cal
    -add(x,y)
    -sub(x,y)
    -multiply(x,y)
    -divide(x,y)

    throw Overflow a+b exceeds ind.max_val
    throw underflow a-b exceeds ind.min_val
    throw MathCalculator ex when div by 0
   */

  // 1
  // val array: Array[Int] = Array.ofDim(Int.MaxValue)

  // 2
  def inifinite(): Int = 1 + inifinite()
  // inifinite()

  // 3
  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculatorException extends RuntimeException("Division by 0")

  object PocketCalculator {
    def add(x: Int, y: Int): Int = {
      val r = x + y
      if(x > 0 && y > 0 && r < 0) throw new OverflowException
      else if(x < 0 && y < 0 && r > 0) throw new UnderflowException
      else r
    }
    def sub(x: Int, y: Int): Int = {
      val r = x - y
      if (x > 0 && y < 0 && r < 0) throw new OverflowException
      else if (x < 0 && y > 0 && r > 0) throw new UnderflowException
      else r
    }

    def multiply(x: Int, y: Int): Int = {
      val r = x * y
      if (x > 0 && y > 0 && r < 0) throw new OverflowException
      else if (x < 0 && y < 0 && r < 0) throw new OverflowException
      else if (x > 0 && y < 0 && r > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && r > 0) throw new UnderflowException
      else r
    }

    def divide(x: Int, y: Int): Int = {
      if(y == 0) throw new MathCalculatorException
      else x / y
    }
  }

  //println(PocketCalculator.add(1,2))
  println(PocketCalculator.add(1,Int.MaxValue))
  //println(PocketCalculator.divide(4, 0))
}
