package com.despegar
package lectures.part2oop

import scala.language.postfixOps

object C12MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String) {
    def likes(movie: String): Boolean = favoriteMovie == movie
    def +(person: Person): String = s"$name is hanging out with ${person.name}"
    def unary_! : String = s"$name, what a heck?!"
    def isAlive: Boolean = true
    def apply(): String = s"Hi my name is $name and i like $favoriteMovie"
  }

  private val mary = new Person("Mary", "Titanic")
  println(mary.likes("Inception"))
  println(mary `likes` "Inception") // infix notation or operator notation

  // "operators" in scala
  private val tom = new Person("Tom", "Inception")
  println(mary + tom)
  println(mary.+(tom))

  println(1 + 2)
  println(1.+(2))

  // prefix notation
  val x = -1
  val y = 1.unary_-
  // unary - + ~ !

  println(!mary)
  println(mary.unary_!)

  // postfix notation
  println(mary.isAlive)
  println(mary isAlive)

  // apply
  println(mary.apply())
  println(mary())
}
