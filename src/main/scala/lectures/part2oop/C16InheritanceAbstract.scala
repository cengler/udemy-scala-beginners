package com.despegar
package lectures.part2oop

object C16InheritanceAbstract extends App {

  // abstract

  abstract class Animal {
    val creatureType: String
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "dog"
    override def eat: Unit = println("guau")
  }

  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"
    override def eat: Unit = println("niam")
    override def eat(animal: Animal): Unit = println(s"I`m a croc and am eating a ${animal.creatureType}")
  }

  val dog = new Dog()
  val crocodile = new Crocodile()
  crocodile.eat(dog)

  // traits vs abstract classes
  // 1 traits do not have constructor parameters
  // 2 multiple traits may bi inhered
  // 3 traits = behavior, abstract = do/thing

  // any
  // any ref -> object -> anyNull        -|
  // any val -> primitive                -----> anyNothing
}
