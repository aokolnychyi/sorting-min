package com.aokolnychyi.sorting

import scala.annotation.tailrec

object ScalaHeapSort {

  def sort[T](array: Array[T])(implicit imp: T => Ordered[T]): Unit = {

    @tailrec
    def sort(index: Int, heapSize: Int): Unit = {
      if (index >= 1) {
        swap(array, 0, index)
        maxHeapify(array, 0, heapSize - 1)
        sort(index - 1, heapSize - 1)
      }
    }

    if (array.length > 0) {
      val lastElementIndex = array.length - 1
      buildMaxHeap(array, index = lastElementIndex / 2)
      sort(index = lastElementIndex, heapSize = array.length)
    }
  }

  @tailrec
  private def buildMaxHeap[T](array: Array[T], index: Int)(implicit imp: T => Ordered[T]): Unit = {
    if (index >= 0) {
      maxHeapify(array, index, array.length)
      buildMaxHeap(array, index - 1)
    }
  }

  @tailrec
  private def maxHeapify[T](
      array: Array[T],
      index: Int,
      heapSize: Int)(implicit imp: T => Ordered[T]): Unit = {

    val indices = Seq(
      Some(index),
      leftChildIndexOp(index, heapSize),
      rightChildIndexOp(index, heapSize)).flatten

    val maxElementIndex = indices.maxBy(array(_))
    if (maxElementIndex != index) {
      swap(array, index, maxElementIndex)
      maxHeapify(array, maxElementIndex, heapSize)
    }
  }

  private def leftChildIndexOp(index: Int, heapSize: Int): Option[Int] = {
    val leftChildIndex = 2 * index + 1
    Some(leftChildIndex).filter(isIndexDefined(_, heapSize))
  }

  private def rightChildIndexOp(index: Int, heapSize: Int): Option[Int] = {
    val rightChildIndex = 2 * index + 2
    Some(rightChildIndex).filter(isIndexDefined(_, heapSize))
  }

  private def isIndexDefined(index: Int, heapSize: Int): Boolean = {
    index >= 0 && index < heapSize
  }

  private def swap[T](seq: Array[T], firstIndex: Int, secondIndex: Int): Unit = {
    val firstIndexElement = seq(firstIndex)
    seq.update(firstIndex, seq(secondIndex))
    seq.update(secondIndex, firstIndexElement)
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
  }
}
