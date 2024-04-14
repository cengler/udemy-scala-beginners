package com.despegar
package lectures.part2oop

object C22Scala3Enum extends App {

  enum Permitions {
    case READ, WRITE, EXECUTE, NONE

    // add fields or methods
    def openDocument(): Unit = {
      if (this == READ) println("Opening document...")
      else println("Reading not allowed")
    }
  }

  val permission = Permitions.READ
  permission.openDocument()

  Permitions.NONE.openDocument()

  // constructor arguments
  enum PermitionsWithBits(bits: Int) {
    case READ extends PermitionsWithBits(4) // 100
    case WRITE extends PermitionsWithBits(2) // 010
    case EXECUTE extends PermitionsWithBits(1) // 001
    case NONE extends PermitionsWithBits(0) // 000
  }

  object PermitionsWithBits {
    def fromBits(bits: Int): PermitionsWithBits = {
      if (bits == 0) return NONE
      else if (bits == 1) return EXECUTE
      ??? // ... more
    }
  }

  // standard API

  println(Permitions.WRITE.ordinal)
  val allPermissions = Permitions.values
  val readPermission = Permitions.valueOf("READ")
}
