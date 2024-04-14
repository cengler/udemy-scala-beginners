package com.despegar
package lectures.part2oop

object C20OOE extends App {

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

  object EmptyList extends MyList[Nothing] {
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

  class ConstantList[+A](h: A, t: MyList[A]) extends MyList[A] {
    def head: A = h
    def tail: MyList[A] = t
    def isEmpty: Boolean = false
    def add[B >: A](element: B): MyList[B] = new ConstantList[B](element, this)
    def printElements: String = { // TODO NO TAIL REC
      if (t.isEmpty) s"$h"
      else s"$h ${t.printElements}"
    }

    def map[B](transformer: MyTransformer[A, B]): MyList[B] = {
      new ConstantList[B](transformer.transform(h), t.map(transformer))
    }
    def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = {
      transformer.transform(h) ++ t.flatMap(transformer)
    }
    def filter(predicate: MyPredicate[A]): MyList[A] = {
      if(predicate.test(head)) new ConstantList[A](head, t.filter(predicate))
      else tail.filter(predicate)
    }
    def ++[B >: A](list: MyList[B]): MyList[B] = {
      ConstantList[B](h, tail ++ list)
    }
  }

  val listOfIntegers = new ConstantList[Int](1, ConstantList[Int](2, new ConstantList[Int](3, EmptyList)))
  val otherListOfIntegers = new ConstantList[Int](4, ConstantList[Int](5, EmptyList))
  val listOfString = new ConstantList[String]("Hello", ConstantList[String]("Scala", EmptyList))

  println(listOfIntegers.toString)
  println(listOfString.toString)

  println(listOfIntegers.map((element: Int) => element * 2).toString)
  println(listOfIntegers.filter((element: Int) => element % 2 == 0).toString)
  println((listOfIntegers ++ otherListOfIntegers).toString)

  println(listOfIntegers.flatMap((element: Int) => {
    new ConstantList[Int](element, new ConstantList[Int](element + 1, EmptyList))
  }))
}
