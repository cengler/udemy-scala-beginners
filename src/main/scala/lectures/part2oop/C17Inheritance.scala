package com.despegar
package lectures.part2oop

import exercises.EmptyList

object C17Inheritance extends App {

  private val empty = EmptyList
  println(empty)

  println(empty.add(1).add(2).add(3).toString)
  
}
