package com.despegar
package lectures.part3functional

object C29MapFlatMapFilterFor extends App {

  val list = List(1,2,3)
  println(list)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatmap
  def toPair = (x: Int) => List(x, x+1)
  println(list.flatMap(toPair))

  // iterations
  val numbers = List(1,2,3,4)
  val chars = List('a', 'b', 'c')
  val colors = List("white", "black")

  val combinations = numbers.flatMap(n => chars.flatMap(c => colors.map(color => s"$c-$n-$color")))
  println(combinations)

  // foreach
  list.foreach(println)

  // for-comprehensions
  val forCombinations = for {
    n <- numbers if n % 2 == 0
    c <- chars
    color <- colors
  } yield s"$c-$n-$color"
  println(forCombinations)

  for {
    n <- numbers
  } println(n)

  // syntax overload
  list.map( x =>
    x * 2
  )

  /*
  1. MyList support comprehensions
  2. Collection Maybe[+T]
    - map, flatMap, filter
   */

  // 1
  // YES

  // 2 - Optional

}
