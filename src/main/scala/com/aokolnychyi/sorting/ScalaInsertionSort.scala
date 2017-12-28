package com.aokolnychyi.sorting

object ScalaInsertionSort {

  def sort[T](list: List[T])(implicit imp: T => Ordered[T]): List[T] = {
    def sort(source: List[T], result: List[T]): List[T] = source match {
      case head :: tail => sort(tail, insert(head, result))
      case Nil => result
    }

    def insert(currentElement: T, list: List[T]): List[T] = list match {
      case head :: tail if currentElement > head => head :: insert(currentElement, tail)
      case _ => currentElement :: list
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
