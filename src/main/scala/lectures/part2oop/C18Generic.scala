package com.despegar
package lectures.part2oop

object C18Generic extends App {

  class MyList[+A] {
    // A GENERIC TYPE
    def add[B >: A](element:B): MyList[B] = ???
  }

  val list = new MyList[Int]
  val stringList = new MyList[String]

  class MyMap[K, V] {

  }

  object MyList {
    def empty[A]: MyList[A] = MyList[A]
  }

  val emptyListInt = MyList.empty[Int]

  // variance problem
  class Animal
  class Dog extends Animal
  class Cat extends Animal

  // 1) YES List[Cat] extends List[Animal] -> covariance
  class CovarianceList[+A]
  val animal: Animal = new Cat()
  val animalList: CovarianceList[Animal] = new CovarianceList[Dog]

  // animalList.add(new Dog()) ?? HARD -> we return a list of animals

  // 2) NO  invariant list
  class InvariantList[A]
  // val invariantAnimalList: InvariantList[Animal] = new InvariantList[Dog] ERROR

  // 3) Hell no! CONTRAVARIANCE
  class ContravarianceList[-A]
  val contravarianceList: ContravarianceList[Cat] = new ContravarianceList[Animal] // NO SENSE ??

  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // bounded types
  class Cage[A <: Animal](animal: Animal)

  class Car
  val car = new Car
  val cage = new Cage(new Dog)

}
