package com.despegar
package lectures.part1basics

import scala.annotation.tailrec

object C8DefaultArgs extends App {

  @tailrec
  private def factorial(x: Int, accumulator: BigInt = 1): BigInt = {
    if (x <= 1) accumulator
    else factorial(x - 1, x * accumulator) // TAIL REC use recursive call as the LAST expression
  }

  factorial(10)

  private def savePicture(format: String = "jpg", width: Int = 60, height: Int = 60): Unit = println("Saving picture")
  savePicture("png")
  savePicture(width = 120)
  savePicture(width = 120, format = "bmp")
}
