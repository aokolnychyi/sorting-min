package com.aokolnychyi.sorting

object ScalaMergeSort {

  def sort[T](list: List[T])(implicit imp: T => Ordered[T]): List[T] = {
    // not tail recursive
    def merge(list: List[T], anotherList: List[T]): List[T] = (list, anotherList) match {
      case (Nil, _) => anotherList
      case (_, Nil) => list
      case (head :: tail, anotherHead :: _) if head <= anotherHead => head :: merge(tail, anotherList)
      case (_ :: _, anotherHead :: anotherTail) => anotherHead :: merge(list, anotherTail)
    }

    // list.length takes time proportional to the length of the sequence
    val middleIndex = list.length / 2
    if (middleIndex == 0) {
      list
    } else {
      // splitAt is based on ListBuffer, which is a buffer implementation backed by a list.
      // It provides constant time prepend and append. Most other operations are linear.
      val (leftSubList, rightSubList) = list.splitAt(middleIndex)
      merge(sort(leftSubList), sort(rightSubList))
    }
  }

  def main(args: Array[String]): Unit = {
    println(sort(List(3, 5, 1, 4, -10)))
    println(sort(List[Int]()))
    println(sort(List(1)))
    println(sort(List(-10, 30, 20, 11)))
  }
}
