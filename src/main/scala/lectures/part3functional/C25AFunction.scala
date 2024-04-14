package com.despegar
package lectures.part3functional

object C25AFunction extends App {

  // DREAM use function as first class elements
  // problem oop

  trait MyFunc[A, B] {
    def apply(element: A): B
  }

  val doubler = new MyFunc[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // function types Function1..22

  val stringToIntConverter = new Function1[String, Int] {
    override def apply(element: String): Int = element.toInt
  }

  // "call"
  println(stringToIntConverter("40") + 2)

  val adder = new Function2[Int, Int, Int] {
    override def apply(x: Int, y: Int): Int = x + y
  }

  println(adder(2, 4))

  val adderSugar1: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(x: Int, y: Int): Int = x + y
  }

  val adder1 = (x: Int, y: Int) => x + y

  /*
  1) a function concat
  2) MyPredicate and MyTransformer
  3) f(int) => (f(int) => int)
   */

  // 1
  val concat = (a: String, b: String) => a + b

  // 2
  abstract class MyList[+A] {
    def head: A

    def tail: MyList[A]

    def isEmpty: Boolean

    def add[B >: A](element: B): MyList[B]

    override def toString: String = s"[$printElements]"

    def printElements: String

    def map[B](transformer: A => B): MyList[B]

    def flatMap[B](transformer: A => MyList[B]): MyList[B]

    def filter(predicate: A => Boolean): MyList[A]

    def ++[B >: A](addList: MyList[B]): MyList[B]
  }

  case object EmptyList extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException

    def tail: MyList[Nothing] = throw new NoSuchElementException

    def isEmpty: Boolean = true

    def add[B](element: B): MyList[B] = ConstantList[B](element, EmptyList)

    def printElements: String = ""

    def map[B](transformer: Nothing => B): MyList[B] = EmptyList

    def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = EmptyList

    def filter(predicate: Nothing => Boolean): MyList[Nothing] = EmptyList

    def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
  }

  case class ConstantList[+A](head: A, tail: MyList[A]) extends MyList[A] {
    def isEmpty: Boolean = false

    def add[B >: A](element: B): MyList[B] = new ConstantList[B](element, this)

    def printElements: String = { // TODO NO TAIL REC
      if (tail.isEmpty) s"$head"
      else s"$head ${tail.printElements}"
    }

    def map[B](transformer: A => B): MyList[B] = {
      new ConstantList[B](transformer(head), tail.map(transformer))
    }

    def flatMap[B](transformer: A => MyList[B]): MyList[B] = {
      transformer(head) ++ tail.flatMap(transformer)
    }

    def filter(predicate: A => Boolean): MyList[A] = {
      if (predicate(head)) new ConstantList[A](head, tail.filter(predicate))
      else tail.filter(predicate)
    }

    def ++[B >: A](list: MyList[B]): MyList[B] = {
      ConstantList[B](head, tail ++ list)
    }
  }

  val listOfIntegers = new ConstantList[Int](1, ConstantList[Int](2, new ConstantList[Int](3, EmptyList)))
  val listOfIntegersClone = new ConstantList[Int](1, ConstantList[Int](2, new ConstantList[Int](3, EmptyList)))
  println(listOfIntegers == listOfIntegersClone)

  // 3

  val superAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y

  val adder3 = superAdder (3)
  println(adder3(4))

  println(superAdder(3)(4)) // curries functions
}


