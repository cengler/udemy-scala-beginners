package com.despegar
package lectures.part1basics

object C9Strings extends App {

  val aString: String = "Hello i am learning scala"

  // JAVA
  println(aString.charAt(2))
  println(aString.substring(2, 5))
  println(aString.split(" ").toList)
  println(aString.startsWith("Hello"))
  println(aString.replace(" ", "_"))
  println(aString.toUpperCase())
  println(aString.length)

  // SCALA
  private val aNumberString = "3454"
  val aNumber = aNumberString.toInt
  println('a' +: aNumberString :+ 'z')
  println(aString.reverse)
  println(aString.take(2))
  // interpolation
  val name = "David"
  val age = 23
  private val greeting = s"Hello, my $name is name and I am $age years old"
  println(greeting)
  private val anotherGreeting = s"Hello, my $name is name and I am will be turning ${age+1} years old"
  println(anotherGreeting)
  // F-interpolation
  private val speed = 1.2f
  private val myth = f"$name can eat $speed%2.2f burgers per minute"
  println(myth)
  // raw-interpolation
  println(raw"This is \n newline")
  val escaped = "This is a \n newline"
  println(raw"This is \n newline. and -> $escaped")
}
