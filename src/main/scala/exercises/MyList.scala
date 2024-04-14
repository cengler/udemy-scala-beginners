package com.despegar
package exercises

/*
def head first element
def tail tail of list
def isEmpty
def add(int: Int)
toString
 */

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  override def toString: String = s"[$printElements]"
  def printElements: String
}

object EmptyList extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B](element: B): MyList[B] = ConstantList[B](element, EmptyList)
  def printElements: String = ""
}

class ConstantList[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new ConstantList[B](element, this)
  def printElements: String = { // TODO NO TAIL REC
    if(t.isEmpty) s"$h"
    else s"$h ${t.printElements}"
  }
}
