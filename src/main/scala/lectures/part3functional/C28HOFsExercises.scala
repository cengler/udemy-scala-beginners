package com.despegar
package lectures.part3functional

object C28HOFsExercises extends App {

  // Expand MyList
  // 1) + ForEach
  // 2) Sort((A, A) -> Int): MyList
  // 3) zipWith(list, (A, A) => B) => MyList[B]
  // 4) fold

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
    def forEach(execute: A => Unit): Unit
    def sort(comparator: (A, A) => Int): MyList[A]
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
    def forEach(execute: Nothing => Unit): Unit = AnyRef
    def sort(comparator: (Nothing, Nothing) => Int) = EmptyList
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
    def forEach(execute: A => Unit): Unit = {
      execute(head)
      tail.forEach(execute)
    }
    def sort(comparator: (A, A) => Int) = {
      // TODO
      sort(comparator)
    }
  }

  val list = ConstantList(1, ConstantList(2, ConstantList(3, EmptyList)))
  println(list)

  // 1
  list.forEach((x: Int) => println(x))

  // 2
}
