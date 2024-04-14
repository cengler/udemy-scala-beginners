package com.despegar
package lectures.part2oop

object C19AnonymousClasses extends App {

  trait Animal {
    def eat(): Unit
  }

  // anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat(): Unit = println("jajaja")
  }

  funnyAnimal.eat()

  class Person(name: String) {
    def sayHi(): Unit = println(s"Hello my name is $name")
  }

  val henry: Person = new Person("Henry") {
    override def sayHi(): Unit = println(s"Im Henry, jajaja")
  }

  henry.sayHi()
}
