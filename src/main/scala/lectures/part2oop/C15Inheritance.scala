package com.despegar
package lectures.part2oop

object C15Inheritance extends App {

  // single class inheritance
  class Animal {
    val creatureType = "wild"
    def eat = println("ñam!")
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("chrunch")
    }
  }

  val cat = new Cat
  cat.crunch

  // CONSTRUCTORS
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: Int) extends Person(name, age)

  // OVERRIDING
  class Dog(override val creatureType: String = "domestic") extends Animal {
    //override val creatureType: String = "domestic"
    override def eat: Unit = {
      super.eat
      println("chrunch, chrunch")
    }
  }

  val dog = new Dog
  dog.eat
  println(dog.creatureType)

  // type substitution (polymorphism)
  val unknownAnimal: Animal = new Dog("k9")
  unknownAnimal.eat
  
  // overRIDING vs overLOADING
  // super

  // preventing overrides
  // 1 - use final on member
  // 2 - use final on the entire class
  // 3 - seal the class = extend class in THIS FILE, prevent extension in other class (sealed class...)
  //
}
