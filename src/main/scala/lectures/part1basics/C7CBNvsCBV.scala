package com.despegar
package lectures.part1basics

object C7CBNvsCBV extends App {

  private def calledByValue(x: Long): Unit = {
    println("by Value: " + x)
    println("by Value: " + x)
  }

  private def calledByName(x: => Long): Unit = {
    println("by Name: " + x)
    println("by Name: " + x)
  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())

  private def infinite(): Int = 1 + infinite()
  private def printFirst(x: Int, y: => Int): Unit = println(x)

  //printFirst(infinite(), 34)
  printFirst(34, infinite())
}
