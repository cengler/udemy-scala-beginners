package com.despegar
package lectures.part3functional

object C26AnonymousFunctions extends App {

  val doubler = new Function1[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  // anonymous function (lambda)
  val doubler2 = (x: Int) => x * 2

  val doubler3: Int => Int = x => x * 2

  // multiple params
  val adder = (x: Int, y: Int) => x + y

  // no params
  val doSomething: () => Unit = () => println("Dooing...")
  val doSomething2 = () => println("Dooing...")

  println(doSomething) // function
  doSomething()
  doSomething2()

  // curly + lambda
  val doSomething4: () => Unit = {
    () => println("Dooing...")
  }

  val incr = (x: Int) => x + 1
  // MOAR syntactic sugar
  val incr2: Int => Int = _ + 1 // x -> x + 1
  val adder2: (Int, Int) => Int = _ + _ // (x,y) -> x + y

  /*
   1) MyList -> lambda (simple)
   2) Rewrite special adder as an anonymous function
   */

  // 2) Rewiten
  val superAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y
  val superAdder2 = (x: Int) => (y: Int) => x + y
  val superAdder3: Int => Int => Int = x => y => x + y
}
