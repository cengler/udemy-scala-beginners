package com.despegar
package lectures.part2oop

import scala.language.postfixOps

object C13MethodNotations extends App {

  /*

    1. Ovorload the + operator
    mary + "the rockstar" - new person "Mary (the rockstar)"

    2. Add an age to the Person class:
    Add a unary + operator → new person with the age + 1
    +mary = mary with the age incrementer

    3. Add a "learns" method in the Person class => "Mary learns Scala"
    add a learnsscala method, calls learns method with "Scala".
    use it in postfix notation.

    4. Overload the apply method
    I mary-apply (2) => "Mary watched movie n times"

   */

  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = favoriteMovie == movie
    def +(person: Person): String = s"$name is hanging out with ${person.name}"

    def +(nik: String): String = s"$name ($nik) likes the movie $favoriteMovie "
    def unary_+ : Person = new Person(name, favoriteMovie, age+1)

    def unary_! : String = s"$name, what a heck?!"
    def isAlive: Boolean = true
    def apply(): String = s"Hi my name is $name and i like $favoriteMovie"
    def apply(times: Int): String = s"$name watched the movie $favoriteMovie $times times"

    def learns(topic: String): String = s"$name leans $topic"
    def learnsScala: String = learns("Scala")
  }

  // 1
  private val mary = new Person("Mary", "Titanic", 37)
  println(mary + "the rockstar")

  // 2
  println(mary.age)
  println((+mary).age)

  // 3
  println(mary.learnsScala)
  println(mary learnsScala)

  // 4
  println(mary(2))
}
