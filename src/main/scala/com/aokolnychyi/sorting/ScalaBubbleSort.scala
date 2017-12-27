package com.aokolnychyi.sorting

import scala.annotation.tailrec

object ScalaBubbleSort {

  def sort[T](array: Array[T])(implicit imp: T => Ordered[T]): Unit = {
    for (outerIndex <- array.indices; innerIndex <- Range(array.length - 1, outerIndex, -1)) {
      val currentElement = array(innerIndex)
      val nextElement = array(innerIndex - 1)
      if (currentElement < nextElement) {
        swap(array, innerIndex, innerIndex - 1)
      }
    }
  }

  private def swap[T](array: Array[T], firstIndex: Int, secondIndex: Int): Unit = {
    val firstIndexElement = array(firstIndex)
    array.update(firstIndex, array(secondIndex))
    array.update(secondIndex, firstIndexElement)
  }

  def sort[T](list: List[T])(implicit imp: T => Ordered[T]): List[T] = {
    def sort(source: List[T], result: List[T]) = source match {
      case Nil => result
      case _ => bubble(source, Nil, result)
    }

    // left -> right
    // the idea is to keep the max element as the head of the source list
    // tempList is used to keep the remaining ones that are less
    @tailrec
    def bubble(source: List[T], tempList: List[T], result: List[T]): List[T] = source match {
      case currentElement :: nextElement :: tail if currentElement > nextElement =>
        bubble(currentElement :: tail, nextElement :: tempList, result)
      case currentElement :: nextElement :: tail =>
        bubble(nextElement :: tail, currentElement :: tempList, result)
      // indicates the end of one pass (i.e., one iteration through the original list)
      // element is the max element at this iteration
      case element :: Nil => sort(tempList, element :: result)
    }

    sort(list, Nil)
  }

  def main(args: Array[String]): Unit = {
    val array1 = Array(3, 5, 1, 4, -10)
    sort(array1)
    println(array1.mkString("[", ",", "]"))
    val array2 = Array[Int]()
    sort(array2)
    println(array2.mkString("[", ",", "]"))
    val array3 = Array(1)
    sort(array3)
    println(array3.mkString("[", ",", "]"))
    val array4 = Array(-10, 30, 20, 11)
    sort(array4)
    println(array4.mkString("[", ",", "]"))

    println(sort(List(3, 5, 1, 4, -10)))
    println(sort(List[Int]()))
    println(sort(List(1)))
    println(sort(List(-10, 30, 20, 11)))
  }

}
