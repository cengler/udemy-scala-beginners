package com.despegar
package lectures.part4patternMatching

import lectures.part3functional.C28HOFsExercises.{ConstantList, EmptyList, MyList}


object C37AllThePatterns extends App {

  // 1 - constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "THE Scala"
    case true => "The Truth"
    case C37AllThePatterns => "A singleton object"
  }

  // 2 - match anything
  // 2.1 wildcard
  val matchAnything = x match {
    case _ =>
  }

  // 2.2 variable
  val matchAVariable = x match {
    case something => s"I've found $something"
  }

  // 3 - tuples
  val aTuple = (1, 2)
  val matchATuple = aTuple match {
    case (1, 1) =>
    case (something, 2) => s"I've found $something"
  }

  val nestedTuple = (1, (2, 3))
  val matchANestedTuple = nestedTuple match {
    case (_, (2, v)) =>
  }
  // PMs can be NESTED!

  // 4 - case classes - constructor pattern
  // PMs can be nested with CCs as well
  val aList: MyList[Int] = ConstantList(1, ConstantList(2, EmptyList))
  val matchAList = aList match {
    case EmptyList =>
    case ConstantList(head, ConstantList(subhead, subtail)) =>
  }

  // 5 - list patterns
  val aStandardList = List(1, 2, 3, 42)
  val standardListMatching = aStandardList match {
    case List(1, _, _, _) => // extractor - advanced
    case List(1, _*) => // list of arbitrary length - advanced
    case 1 :: List(_) => // infix pattern
    case List(1, 2, _) :+ 42 => "lala" // infix pattern
  }

  // 6 - type specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => // explicit type specifier
    case _ =>
  }

  // 7 - name binding
  val nameBindingMatch = aList match {
    case nonEmptyList@ConstantList(_, _) => // name binding => use the name later(here)
    case ConstantList(1, rest@ConstantList(2, _)) => // name binding inside nested patterns
  }

  // 8 - multi-patterns
  /*val multipattern = aList match { TODO ver por que rompe
    case EmptyList | ConstantList(0, _) => // compound pattern (multi-pattern)
  }*/

  // 9 - if guards
  val secondElementSpecial = aList match {
    case ConstantList(_, ConstantList(specialElement, _)) if specialElement % 2 == 0 =>
  }

  // ALL.

  // Question.

  val numbers = List(1, 2, 3)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "a list of strings"
    case listOfNumbers: List[Int] => "a list of numbers"
    case _ => ""
  }

  println(numbersMatch)
  // JVM trick question

  //case listOfStrings: List => "a list of strings"
  //case listOfNumbers: List => "a list of numbers"


}
