package com.aokolnychyi.sorting

import scala.annotation.tailrec

object ScalaSelectionSort {

  def sort[T](list: List[T])(implicit imp: T => Ordered[T]): List[T] = {
    def sort(source: List[T], result: List[T]) = source match {
      // the current max is head
      case head :: tail => select(head, tail, Nil, result)
      case Nil => result
    }

    // left -> right
    @tailrec
    def select(max: T, source: List[T], temp: List[T], result: List[T]): List[T] = source match {
      case head :: tail if head > max => select(head, tail, max :: temp, result)
      case head :: tail => select(max, tail, head :: temp, result)
      case Nil => sort(temp, max :: result)
    }

    sort(list, Nil)
  }

  def main(args: Array[String]): Unit = {
    println(sort(List(3, 5, 1, 4, -10)))
    println(sort(List[Int]()))
    println(sort(List(1)))
    println(sort(List(-10, 30, 20, 11)))
  }
}
