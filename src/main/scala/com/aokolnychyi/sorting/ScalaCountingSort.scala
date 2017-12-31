package com.aokolnychyi.sorting

object ScalaCountingSort {

  def sort(inputArray: Array[Int]): Unit = {
    if (inputArray.length > 1) {
      val maxElement = inputArray.max
      val outputArray = new Array[Int](inputArray.length)
      val auxiliaryArray = new Array[Int](maxElement + 1) // we need to count 0 as well

      // count the number of elements
      for (element <- inputArray) {
        auxiliaryArray(element) += 1
      }
      // count the number of smaller or equal elements
      for (index <- 1 to maxElement) {
        auxiliaryArray(index) += auxiliaryArray(index - 1)
      }

      for (index <- inputArray.length - 1 to 0 by -1) {
        val element = inputArray(index)
        val indexAfterSorting = auxiliaryArray(element) - 1
        outputArray(indexAfterSorting) = element
        // do not forget to decrement the number of small or equal elements
        auxiliaryArray(element) = auxiliaryArray(element) - 1
      }

      // copy all sorted entries from outputArray to inputArray
      Array.copy(outputArray, 0, inputArray, 0, inputArray.length)
    }
  }

  def main(args: Array[String]): Unit = {
    val array1 = Array(3, 5, 1, 4, 10)
    sort(array1)
    println(array1.mkString("[", ",", "]"))
    val array2 = Array[Int]()
    sort(array2)
    println(array2.mkString("[", ",", "]"))
    val array3 = Array(1)
    sort(array3)
    println(array3.mkString("[", ",", "]"))
    val array4 = Array(0, 30, 20, 11)
    sort(array4)
    println(array4.mkString("[", ",", "]"))
  }
}
