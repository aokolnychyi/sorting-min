package com.aokolnychyi.sorting

object ScalaQuickSort {

  // inspired by https://stackoverflow.com/a/2962799/4108401
  def sort[T](list: List[T])(implicit imp: T => Ordered[T]): List[T] = {

    // rightElements represents all elements that are bigger that any elements in list
    def sort(list: List[T])(rightElements: List[T]): List[T] = list match {
      case Nil => rightElements
      case head :: Nil => head :: rightElements
      case pivot :: _ =>
        val (smallerElements, equalElements, biggerElements) =
          list.foldLeft((List.empty[T], List.empty[T], List.empty[T])) {
            case ((currentSmaller, currentEqual, currentBigger), element) =>
              if (element < pivot)
                (element :: currentSmaller, currentEqual, currentBigger)
              else if (element == pivot)
                (currentSmaller, element :: currentEqual, currentBigger)
              else
                (currentSmaller, currentEqual, element :: currentBigger)
          }
        sort(smallerElements)(equalElements ::: sort(biggerElements)(rightElements))
    }

    sort(list)(Nil)
  }

  def main(args: Array[String]): Unit = {
    println(sort(List(3, 5, 1, 4, -10)))
    println(sort(List[Int]()))
    println(sort(List(1)))
    println(sort(List(-10, 30, 20, 11)))
  }
}
