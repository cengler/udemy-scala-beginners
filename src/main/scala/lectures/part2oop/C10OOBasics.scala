package com.despegar
package lectures.part2oop

object C10OOBasics extends App {

  class Person(name: String, val age: Int = 0) { // constructor
    //                         ^ converts to field

    val x = 2 // <- val are fields
    println("Construction")

    def greet(name: String): Unit = {
      println(s"${this.name} Says: Hello $name!")
    }

    // overloading
    def greet(): Unit = {
      println(s"Hello I am $name")
    }

    // overloading constructors
    def this(name: String) = this(name, 0)

    def this() = this("John")
  }

  private val person = new Person("John", 37)
  println(person.age)
  println(person.x)

  person.greet("Daniel")
  person.greet()
}

