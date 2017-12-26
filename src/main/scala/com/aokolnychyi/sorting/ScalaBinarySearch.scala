package com.aokolnychyi.sorting

import scala.annotation.tailrec
import scala.util.Try

object ScalaBinarySearch {

  implicit class SortedArray[T](private val array: Array[T])(implicit imp: T => Ordered[T]) {

    require(isSorted(array), "The array must be sorted!")

    def findIndex(element: T): Option[Int] = {

      @tailrec
      def inner(lowerBound: Int, upperBound: Int): Option[Int] = {
        if (lowerBound > upperBound) {
          None
        } else {
          // val middleIndex = (lowerBound + upperBound) / 2 --> can cause an overflow
          val middleIndex = lowerBound + (upperBound - lowerBound) / 2
          middleIndex match {
            case _ if array(middleIndex) < element => inner(middleIndex + 1, upperBound)
            case _ if array(middleIndex) > element => inner(lowerBound, middleIndex - 1)
            case _ => Some(middleIndex)
          }
        }
      }

      inner(0, array.length - 1)
    }

    private def isSorted(array: Array[T]): Boolean = array match {
      case Array() => true
      case Array(_) => true
      case _ => array.sliding(2).forall { case Array(element, nextElement) => element < nextElement }
    }

  }

  def main(args: Array[String]): Unit = {

    val array1 = Array(1, 2, 3, 4, 5, 6)
    val array2 = Array(-1, 0, 2, 5, 6)
    val array3 = Array(-1)
    val array5 = Array(2, 3, 1)
    val array6 = Array(3, 1)

    println(s"Index of 3 in ${array1.mkString("[", ",", "]")}: ${array1.findIndex(3)}")
    println(s"Index of 1 in ${array1.mkString("[", ",", "]")}: ${array1.findIndex(1)}")
    println(s"Index of 6 in ${array1.mkString("[", ",", "]")}: ${array1.findIndex(6)}")
    println(s"Index of 11 in ${array1.mkString("[", ",", "]")}: ${array1.findIndex(11)}")

    println(s"Index of 2 in ${array2.mkString("[", ",", "]")}: ${array2.findIndex(2)}")
    println(s"Index of -1 in ${array2.mkString("[", ",", "]")}: ${array2.findIndex(-1)}")
    println(s"Index of 5 in ${array2.mkString("[", ",", "]")}: ${array2.findIndex(5)}")
    println(s"Index of 6 in ${array2.mkString("[", ",", "]")}: ${array2.findIndex(6)}")

    println(s"Index of -1 in ${array3.mkString("[", ",", "]")}: ${array3.findIndex(-1)}")
    println(s"Index of 1 in ${array3.mkString("[", ",", "]")}: ${array3.findIndex(1)}")

    println(Try(array5.findIndex(1)))
    println(Try(array6.findIndex(3)))
  }
}
