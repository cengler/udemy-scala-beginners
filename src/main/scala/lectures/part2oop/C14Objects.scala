package com.despegar
package lectures.part2oop

object C14Objects extends App {

  // SCALA has no class level functionality ("static")

  object Person {
    // STATIC / CLASS
    val N_EYES = 2
    def canFly: Boolean = false
    def from(mother: Person, father: Person, childName: String) = new Person(childName)
    def apply(mother: Person, father: Person, childName: String) = new Person(childName)
  }
  class Person(name: String) {
    // INSTANCE LEVEL
  }
  // COMPANIONS

  println(Person.N_EYES)
  println(Person.canFly)

  // OBJECT = SINGLETON

  val mary = new Person("Mary")
  val thom = new Person("Thom")
  println(mary == thom)

  val child1 = Person.from(mary, thom, "Viggo")
  val child2 = Person(mary, thom, "Nika")

  // SCALA APP = Scala Object + def main


}
