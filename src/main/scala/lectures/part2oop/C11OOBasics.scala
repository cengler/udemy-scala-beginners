package com.despegar
package lectures.part2oop

object C11OOBasics extends App {

  /*
  NOVEL(name, yearOfRelease, author)
  - authorAge
  - isWrittenBy(author)
  - copy(yearOfRelease)
  WRITER(first, surname, year)
  m full name
   */


  class Writer(firstName: String, surname: String, val year: Int) {
    def fullName(): String = s"$firstName $surname"
  }

  class Novel(name: String, yearOfRelease: Int, author: Writer) {
    def authorAge(): Int = yearOfRelease - author.year
    def isWrittenBy(writer: Writer): Boolean = author == writer
    def copy(yearOfRelease: Int): Novel = new Novel(name, yearOfRelease, author)
  }

  val author = new Writer("Juan", "Perez", 1000)
  val imposter = new Writer("Juan", "Perez", 1000)
  val novel = new Novel("Book1", 1034, author)

  println(novel.authorAge())
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(imposter))

  /*
  Counter(int)
  - current
  - increment/decrement -> new counter
  - overload increment/decrement -> new counter
   */

  class Counter(val current: Int = 0) {

    private def increment(): Counter = {
      println("incrementing")
      Counter(current + 1)
    }
    private def decrement(): Counter = {
      println("decrementing")
      Counter(current - 1)
    }

    def increment(amount: Int = 1): Counter = {
      if(amount <= 0) this
      else increment().increment(amount-1)
    }
    def decrement(amount: Int = 1): Counter = {
      if (amount <= 0) this
      else decrement().decrement(amount-1)
    }
    def print(): Unit = {
      println(current)
    }
  }

  private val counter = Counter()
  counter.print()
  counter.increment().print()
  counter.increment().increment().print()
  counter.increment(5).print()
  counter.decrement(3).print()
}

