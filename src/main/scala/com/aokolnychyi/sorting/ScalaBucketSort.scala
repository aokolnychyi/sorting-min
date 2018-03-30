package com.aokolnychyi.sorting

import scala.collection.mutable.ArrayBuffer

object ScalaBucketSort {

  // does not handle negative values
  def sort(array: Array[Int], numberOfBuckets: Int): Unit = {
    if (array.length > 0) {
      val minElement = array.min
      val maxElement = array.max
      val bucketInterval = (minElement + maxElement) / numberOfBuckets

      val buckets = ArrayBuffer.fill(numberOfBuckets)(ArrayBuffer.empty[Int])

      for (element <- array) {
        // in case of max element -> bucketIndex = numberOfBuckets
        val bucketIndex = Math.min((element - minElement) / bucketInterval, numberOfBuckets - 1)
        buckets(bucketIndex) += element
      }

      val sortedBuckets = buckets.map(_.sorted)

      var currentIndex = 0
      for (sortedBucket <- sortedBuckets; bucketElement <- sortedBucket) {
        array(currentIndex) = bucketElement
        currentIndex += 1
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val array1 = Array(3, 5, 1, 4, 10)
    // min = 1, max = 10, bucketInterval = 11/3 = 3, intervals [1, 3], [4, 6], [7, infinity)
    sort(array1, 3)
    println(array1.mkString("[", ",", "]"))
    val array2 = Array[Int]()
    sort(array2, 3)
    println(array2.mkString("[", ",", "]"))
    val array3 = Array(1)
    sort(array3, 1)
    println(array3.mkString("[", ",", "]"))
    val array4 = Array(10, 30, 20, 11)
    sort(array4, 4)
    println(array4.mkString("[", ",", "]"))
  }

}
