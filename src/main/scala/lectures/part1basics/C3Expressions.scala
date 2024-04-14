package com.despegar
package lectures.part1basics

object C3Expressions extends App {

  val x = 1 + 2 // EXPRESSION
  println(2)

  // MATH EX
  println(2 + 3 * 3)
  //  + - * / & | ^ << >> >>> (right shift with zero extension)

  // BOOLEAN EX
  println(1 == x)
  // BINARY == != > < >= <=
  // UNARY !

  var y = 1
  y += 3
  // += -= *= /= SIDE EFFECTS

  // Instructions (DO) vs Expressions (VALUE)

  // if expression
  private val aCondition = true
  private val aConditionedValue = if(aCondition) 4 else 2 // IF EXPRESSION
  println(aConditionedValue)
  println(if(aCondition) 4 else 2)

  var i = 0
  while (i < 10) {
    println(i)
    i += 1
  }
  // ^ NEVER WRITE THIS AGAIN

  // EVERYTHING IN SCALA IS AN EXPRESSION

  private val aWiredValue: Unit = y = 3 // Unit === VOID
  println(aWiredValue)

  // SIDE EFFECTS: -> print() / while / reassign

  // CODE BLOCKS

  val aCodeBlock = {
    val a = 2
    val b = a + 1
    if (b > 2) "hello" else "goodbye" // LAST EXPRESSION LINE
  }

  // 1 dif "hello world" vs print("hello world") STRING VS UNIT + SIDE EFFECT PRINTING HW
  // 2
  private val someVal = { // BOOLEAN
    2 < 3
  }
  // 3
  val someOther = { // INT 42
    // if(someVal) 239 else 985
    42
  }

}
