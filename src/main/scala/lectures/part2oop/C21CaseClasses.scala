package com.despegar
package lectures.part2oop

object C21CaseClasses extends App {

  // equals, hashcode, toString

  case class Person(name: String, age: Int)

  // 1) class parameters are fields
  val thom = Person("Thom", 3)
  println(thom.age)

  // 2) sensible toString
  println(thom)

  // 3) equals and hashcode
  val thom2 = Person("Thom", 3)
  println(thom == thom2)

  // 4) + copy methods
  val thom3 = thom.copy()
  val thom4 = thom.copy(age = 4)

  // 5) have companion objects
  val thePerson = Person
  val mary = Person("Mary", 34) // no new use!

  // 6) are serializable
  // akka

  // 7) extractor patterns
  // PATTERN MATCHING

  // 8) case objects
  case object Argentina {
    def name: String = "Argentina e islas del atlantico sur"
  }

  // Exercises

  // Expand MyList -> use case classes and case objects

  trait MyPredicate[-T] {
    def test(element: T): Boolean
  }

  trait MyTransformer[-A, B] {
    def transform(element: A): B
  }

  abstract class MyList[+A] {
    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    def add[B >: A](element: B): MyList[B]
    override def toString: String = s"[$printElements]"
    def printElements: String
    def map[B](transformer: MyTransformer[A, B]): MyList[B]
    def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
    def filter(predicate: MyPredicate[A]): MyList[A]
    // concatenation
    def ++[B >: A](addList: MyList[B]): MyList[B]
  }

  case object EmptyList extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException
    def tail: MyList[Nothing] = throw new NoSuchElementException
    def isEmpty: Boolean = true
    def add[B](element: B): MyList[B] = ConstantList[B](element, EmptyList)
    def printElements: String = ""
    def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = EmptyList
    def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = EmptyList
    def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = EmptyList
    def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
  }

  case class ConstantList[+A](head: A, tail: MyList[A]) extends MyList[A] {
    def isEmpty: Boolean = false
    def add[B >: A](element: B): MyList[B] = new ConstantList[B](element, this)
    def printElements: String = { // TODO NO TAIL REC
      if (tail.isEmpty) s"$head"
      else s"$head ${tail.printElements}"
    }
    def map[B](transformer: MyTransformer[A, B]): MyList[B] = {
      new ConstantList[B](transformer.transform(head), tail.map(transformer))
    }
    def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = {
      transformer.transform(head) ++ tail.flatMap(transformer)
    }
    def filter(predicate: MyPredicate[A]): MyList[A] = {
      if (predicate.test(head)) new ConstantList[A](head, tail.filter(predicate))
      else tail.filter(predicate)
    }
    def ++[B >: A](list: MyList[B]): MyList[B] = {
      ConstantList[B](head, tail ++ list)
    }
  }

  val listOfIntegers = new ConstantList[Int](1, ConstantList[Int](2, new ConstantList[Int](3, EmptyList)))
  val listOfIntegersClone = new ConstantList[Int](1, ConstantList[Int](2, new ConstantList[Int](3, EmptyList)))

  println(listOfIntegers == listOfIntegersClone)

}
