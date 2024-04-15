package com.despegar
package lectures.part3functional

object C28HOFsExercises extends App {

  // Expand MyList
  // 1) + ForEach
  // 2) Sort((A, A) -> Int): MyList
  // 3) zipWith(list, (A, A) => B) => MyList[B]
  // 4) fold

  // 5) curry and formCurry
  // 6) compose and andThen

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
    def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C]
    def fold[B](start: B)(operator: (B, A) => B): B
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
    // HOFs
    def forEach(execute: Nothing => Unit): Unit = ()
    def sort(comparator: (Nothing, Nothing) => Int): MyList[Nothing] = EmptyList
    def zipWith[B,C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] = {
      if(!list.isEmpty) throw new RuntimeException("Lists dont match length")
      else EmptyList
    }
    def fold[B](start: B)(operator: (B, Nothing) => B): B = start
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
    // HOFS
    def forEach(f: A => Unit): Unit = {
      f(head)
      tail.forEach(f)
    }
    def sort(comparator: (A, A) => Int): MyList[A] = {

      def insert(x: A, sortedList: MyList[A]): ConstantList[A] =
        if (sortedList.isEmpty) new ConstantList[A](x, EmptyList)
        else if (comparator(x, sortedList.head) >= 0) new ConstantList[A](x, sortedList)
        else new ConstantList[A](sortedList.head, insert(x, sortedList.tail))

      val sortedTail = tail.sort(comparator)
      insert(head, sortedTail)
    }

    def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] = {
      if (list.isEmpty) throw new RuntimeException("Lists dont match length")
      else ConstantList(zip(head, list.head), tail.zipWith(list.tail, zip))
    }

    def fold[B](start: B)(operator: (B, A) => B): B = tail.fold(operator(start, head))(operator)


  }

  val list = ConstantList(1, ConstantList(2, ConstantList(3, EmptyList)))
  println(list)

  // 1 foreach
  list.forEach((x: Int) => println(x))

  // 2 sort
  val listOfIntegers = new ConstantList[Int](1, ConstantList[Int](2, new ConstantList[Int](3, EmptyList)))
  println(listOfIntegers.sort((x,y)=>x-y))

  // 3 zip
  val list2 = ConstantList("a", ConstantList("b", ConstantList("c", EmptyList)))
  println(list.zipWith(list2, (a: Int, b: String) => s"$a-$b"))
  println(list.zipWith(list2, _ + "-" + _))

  // 4 fold / one form of reduce
  println(list.fold(0)(_ + _))

  // 5 curry and formCurry
  def toCurry(f: (Int,Int)=>Int): Int => Int => Int = x => y => f(x, y)
  def fromCurry(f: Int=>Int=>Int): (Int,Int) => Int = (x, y) => f(x)(y)

  // 6 compose and andThen -> see FunctionX
  def compose[A,B,C](f: B => C, g: A => B): A => C = x => f(g(x))
  def andThen[A,B,C](f: A => B, g: B => C): A => C = x => g(f(x))

  def superAdder: Int => Int => Int = toCurry(_ + _)
  def add4 = superAdder(4)
  println(add4(6))

  def simpleAdder = fromCurry(superAdder)
  println(simpleAdder(4,6))

  def add2 = (x: Int) => x + 2
  def times3 = (x: Int) => x * 3

  println(add2.compose(times3)(1))
  println(add2.andThen(times3)(1))

  // for-comprehensions
  val forCombinations = for {
    n <- list // TODO no anda por mas que tengo filter -> if n % 2 == 0
    s <- list2
  } yield s"$n-$s"
  println(forCombinations)

}
